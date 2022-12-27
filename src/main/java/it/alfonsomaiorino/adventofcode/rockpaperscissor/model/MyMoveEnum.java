package it.alfonsomaiorino.adventofcode.rockpaperscissor.model;

import java.util.Arrays;

public enum MyMoveEnum {

    ROCK('X', 1L, 'A'),
    PAPER('Y', 2L, 'B'),
    SCISSORS('Z', 3L, 'C');

    private Character strategyCode;
    private Long pointsForMove;
    private Character realMoveCode;

    MyMoveEnum(Character strategyCode, Long pointsForMove, Character realMoveCode) {
        this.strategyCode = strategyCode;
        this.pointsForMove = pointsForMove;
        this.realMoveCode = realMoveCode;
    }

    public Character getStrategyCode() {
        return strategyCode;
    }

    public Long getPointsForMove() {
        return pointsForMove;
    }

    public static MyMoveEnum getByStrategyCode(Character strategyCode) {
        return Arrays.stream(MyMoveEnum.values())
                .filter(myMove -> strategyCode.equals(myMove.getStrategyCode()))
                .findFirst()
                .orElseThrow();
    }

    public static MyMoveEnum getByRealMoveCode(Character realMoveCode) {
        return Arrays.stream(MyMoveEnum.values())
                .filter(myMove -> realMoveCode.equals(myMove.getStrategyCode()))
                .findFirst()
                .orElseThrow();
    }

}
