package it.alfonsomaiorino.adventofcode.rockpaperscissor;

import it.alfonsomaiorino.adventofcode.rockpaperscissor.service.PointsCalculator;

import java.io.IOException;

public class MainClass {

    public static void main(String[] args) throws IOException {
        System.out.println("Total points: " + PointsCalculator.calculatePoints(PointsCalculator.parseStrategyFile()));
        System.out.println("Total points with correct strategy: " + PointsCalculator.calculatePointsWithFullStrategy(PointsCalculator.parseStrategyFile()));
    }

}
