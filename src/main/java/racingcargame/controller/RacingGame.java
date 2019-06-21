package racingcargame.controller;

import racingcargame.view.OutputView;
import racingcargame.model.*;
import racingcargame.view.InputView;

import java.util.*;

public class RacingGame {

    public void play() {
        Cars cars = generateCars();
        Turns turns = generateTurns();

        Cars resultCars = doTheTurns(cars, turns);
        Winners winners = new Winners(resultCars);

        OutputView.printWinners(winners);
    }

    private Cars generateCars() {
        Optional<Cars> cars;
        do {
            cars = generateOptionalCars();
        } while (!cars.isPresent());
        return cars.get();
    }

    private static Optional<Cars> generateOptionalCars() {
        try {
            return Optional.ofNullable(new Cars(InputView.askCarNames()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Turns generateTurns() {
        Optional<Turns> turns;
        do {
            turns = generateOptionalTurns();
        } while (!turns.isPresent());
        return turns.get();
    }

    private Optional<Turns> generateOptionalTurns() {
        try {
            return Optional.ofNullable(new Turns(InputView.askTotalTurns()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Cars doTheTurns(Cars cars, Turns turns) {
        OutputView.printResultSentence();
        Cars resultCars = cars.cloneCars();
        int currentTurn = 0;
        while (turns.isTurnsNotFinished(currentTurn)) {
            List<Integer> randomNumbers = generateRandomNumbers(cars.getSize());
            resultCars = resultCars.moveTheCars(randomNumbers);
            OutputView.printCarMovements(resultCars);
            currentTurn++;
        }
        return resultCars;
    }

    private List<Integer> generateRandomNumbers(int size) {
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int currentRandom = (int) (Math.random() * 10);
            randomNumbers.add(currentRandom);
        }
        return randomNumbers;
    }
}
