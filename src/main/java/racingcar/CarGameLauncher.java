package racingcar;

import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.*;

public class CarGameLauncher {
    public static void main(String[] args) {
        doCarGame();
    }

    public static void doCarGame() {
        Cars cars = new Cars(InputView.askAndReceiveCarNames());
        RacingGame racingGame = RacingGame.instantiateRacingGame();
        OutputView.printTheProcess(cars, racingGame);
        Winners winners = new Winners();
        winners.decideWinners(cars);
        OutputView.printWinners(winners);
    }
}
