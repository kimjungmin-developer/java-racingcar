package calculator.controller;

import calculator.model.Calculator;
import calculator.model.ExtractedInformation;
import calculator.view.InputView;
import calculator.view.OutputView;


public class StringCalculatorLauncher {
    public static void main(String[] args) {
        doCalculate();
    }

    public static void doCalculate() {
        try {
            ExtractedInformation extractedInfo = generateExtractedInformation();
            Calculator calculator = new Calculator(extractedInfo);

            int result = calculator.calculate();

            OutputView.printResult(result);
        } catch (Exception e) {
            System.out.println("잘못된 입력이 있습니다!");
            doCalculate();
        }

    }

    private static ExtractedInformation generateExtractedInformation() {
        try {
            ExtractedInformation extractedInfo = new ExtractedInformation(InputView.askAndReceiveExpression());
            return extractedInfo;
        } catch (Exception e) {
            return generateExtractedInformation();
        }
    }
}
