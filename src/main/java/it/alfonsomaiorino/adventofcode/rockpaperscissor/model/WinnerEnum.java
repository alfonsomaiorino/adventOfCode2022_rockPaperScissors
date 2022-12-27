package it.alfonsomaiorino.adventofcode.rockpaperscissor.model;

import java.util.Arrays;

public enum WinnerEnum {

    ME(6L, 'Z'),
    OPPONENT(0L, 'X'),
    DRAW(3L, 'Y');

    private Long points;
    private Character strategyCode;

    WinnerEnum(Long points, Character strategyCode) {
        this.points = points;
        this.strategyCode = strategyCode;
    }

    public Long getPoints() {
        return points;
    }

    public Character getStrategyCode() {
        return strategyCode;
    }

    public static WinnerEnum getByStrategyCode(Character strategyCode) {
        return Arrays.stream(WinnerEnum.values())
                .filter(el -> strategyCode.equals(el.getStrategyCode()))
                .findFirst()
                .orElseThrow();
    }

}
