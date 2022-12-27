package it.alfonsomaiorino.adventofcode.rockpaperscissor.model;

import java.util.Arrays;

public enum MyMoveEnum {

    ROCK('X', 1L),
    PAPER('Y', 2L),
    SCISSORS('Z', 3L);

    private Character strategyCode;
    private Long pointsForMove;

    MyMoveEnum(Character strategyCode, Long pointsForMove) {
        this.strategyCode = strategyCode;
        this.pointsForMove = pointsForMove;
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

}
