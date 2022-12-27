package it.alfonsomaiorino.adventofcode.rockpaperscissor.model;

import java.util.Arrays;

public enum OpponentMoveEnum {

    ROCK('A'),
    PAPER('B'),
    SCISSORS('C');

    private Character strategyCode;

    OpponentMoveEnum(Character strategyCode) {
        this.strategyCode = strategyCode;
    }

    public Character getStrategyCode() {
        return strategyCode;
    }

    public static OpponentMoveEnum getByStrategyCode(Character strategyCode) {
        return Arrays.stream(OpponentMoveEnum.values())
                .filter(myMove -> strategyCode.equals(myMove.getStrategyCode()))
                .findFirst()
                .orElseThrow();
    }

}
