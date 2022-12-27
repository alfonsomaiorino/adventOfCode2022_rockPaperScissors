package it.alfonsomaiorino.adventofcode.rockpaperscissor.service;

import it.alfonsomaiorino.adventofcode.rockpaperscissor.model.MyMoveEnum;
import it.alfonsomaiorino.adventofcode.rockpaperscissor.model.OpponentMoveEnum;
import it.alfonsomaiorino.adventofcode.rockpaperscissor.model.WinnerEnum;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class PointsCalculator {

    public static List<Pair<Character, Character>> parseStrategyFile() throws IOException {
        var classLoader = Thread.currentThread().getContextClassLoader();
        var url = classLoader.getResource("game_strategy.txt");
        var path = Paths.get(Optional.ofNullable(url).map(u -> {
            try {
                return u.toURI();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return null;
        }).orElseThrow());
        var lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        var strategyMap = new ArrayList<Pair<Character, Character>>();

        lines.forEach(line -> parseStrategyMoveLine(line, strategyMap));

        return strategyMap;
    }

    private static void parseStrategyMoveLine(String strategyLine, ArrayList<Pair<Character, Character>> strategyMap) {
        var lineWithoutSpaces = strategyLine.replace(" ", "");
        strategyMap.add(Pair.of(lineWithoutSpaces.charAt(0), lineWithoutSpaces.charAt(1)));
    }

    public static Long calculatePoints(List<Pair<Character, Character>> strategyMap) {
        var totalPoints = new AtomicLong(0L);
        strategyMap.forEach(strategyLine -> {
            var opponentMove = OpponentMoveEnum.getByStrategyCode(strategyLine.getLeft());
            var myMove = MyMoveEnum.getByStrategyCode(strategyLine.getRight());
            var winner = getWinner(opponentMove, myMove);
            totalPoints.addAndGet(calculatePointsForSingleMatch(myMove, winner));
        });
        return totalPoints.get();
    }

    private static WinnerEnum getWinner(OpponentMoveEnum opponentMove, MyMoveEnum myMove) {
        if(isDraw(opponentMove, myMove)) {
            return WinnerEnum.DRAW;
        } else if(imWinner(opponentMove, myMove)){
            return WinnerEnum.ME;
        } else {
            return WinnerEnum.OPPONENT;
        }
    }

    private static boolean isDraw(OpponentMoveEnum opponentMove, MyMoveEnum myMove) {
        return (opponentMove == OpponentMoveEnum.ROCK && myMove == MyMoveEnum.ROCK)
                || (opponentMove == OpponentMoveEnum.PAPER && myMove == MyMoveEnum.PAPER)
                || (opponentMove == OpponentMoveEnum.SCISSORS && myMove == MyMoveEnum.SCISSORS);
    }

    private static boolean imWinner(OpponentMoveEnum opponentMove, MyMoveEnum myMove) {
        return (myMove == MyMoveEnum.ROCK && opponentMove == OpponentMoveEnum.SCISSORS)
                || (myMove == MyMoveEnum.PAPER && opponentMove == OpponentMoveEnum.ROCK)
                || (myMove == MyMoveEnum.SCISSORS && opponentMove == OpponentMoveEnum.PAPER);
    }

    private static Long calculatePointsForSingleMatch(MyMoveEnum myMove, WinnerEnum winner) {
        return myMove.getPointsForMove() + winner.getPoints();
    }

    public static Long calculatePointsWithFullStrategy(List<Pair<Character, Character>> strategyMap) {
        var totalPoints = new AtomicLong(0L);
        strategyMap.forEach(strategyLine -> {
            var opponentMove = OpponentMoveEnum.getByStrategyCode(strategyLine.getLeft());
            var winner = WinnerEnum.getByStrategyCode(strategyLine.getRight());
            var myMove = getMyMoveByOpponentsMoveAndWinningStrategy(opponentMove, winner);
            totalPoints.addAndGet(calculatePointsForSingleMatch(myMove, winner));
        });
        return totalPoints.get();
    }

    private static MyMoveEnum getMyMoveByOpponentsMoveAndWinningStrategy(OpponentMoveEnum opponentMove, WinnerEnum winner) {
        return switch(opponentMove) {
            case ROCK -> switch(winner) {
                case ME -> MyMoveEnum.PAPER;
                case OPPONENT -> MyMoveEnum.SCISSORS;
                case DRAW -> MyMoveEnum.ROCK;
            };
            case PAPER -> switch(winner) {
                case ME -> MyMoveEnum.SCISSORS;
                case OPPONENT -> MyMoveEnum.ROCK;
                case DRAW -> MyMoveEnum.PAPER;
            };
            case SCISSORS -> switch(winner) {
                case ME -> MyMoveEnum.ROCK;
                case OPPONENT -> MyMoveEnum.PAPER;
                case DRAW -> MyMoveEnum.SCISSORS;
            };
        };
    }

}
