package racingcar;

import java.util.*;

public class CarException {
    public static List<String> askCarNamesException(){
        try {
            List<String> names = askAndReceiveCarNames();
            checkConditionsForNames(names);
            return names;
        } catch(Exception e) {
            return Car.askCarNames();
        }
    }

    public static void checkConditionsForNames(List<String> names) {
        if (isWhiteSpaceOnly(names) || isDuplicate(names) || isOverLimit(names)){
            throw new IllegalArgumentException();
        }
    }

    public static List<String> askAndReceiveCarNames(){
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        Scanner scanner = new Scanner(System.in);
        String names = scanner.nextLine();
        names = names.replaceAll("\\s+","");
        List<String> nameList = new ArrayList<>(Arrays.asList(names.split(",")));
        return nameList;
    }

    public static boolean isWhiteSpaceOnly(List<String> names) {
        boolean whiteSpaceOnly = false;
        for(int i=0; i<names.size() && !whiteSpaceOnly; i++){
            whiteSpaceOnly = names.get(i).isEmpty();
        }
        if (whiteSpaceOnly) {
            System.out.println("이름중에 공백으로만 이루어진 이름이 있습니다");
        }
        return whiteSpaceOnly;
    }

    public static boolean isDuplicate(List<String> names) {
        Set<String> nameSet = new HashSet<>(names);
        if (names.size() != nameSet.size()) {
            System.out.println("이름에 중복이 있습니다!");
            return true;
        }
        return false;
    }

    public static boolean isOverLimit(List<String> names) {
        List<String> namesCopy = new ArrayList<>(names);
        boolean overLimit = false;
        while (!overLimit && !namesCopy.isEmpty()) {
            overLimit = checkLimit(names.get(0));
            namesCopy.remove(0);
        }
        return overLimit;
    }

    public static boolean checkLimit(String name) {
        if (name.length() > 5 || name.length() <= 0) {
            System.out.println("이름은 0글자 이상 5글자 이하만 입력해 주세요!");
            return true;
        }
        return false;
    }

    public static int askTotalTurnsException() {
        try {
            int userInput = askAndReceiveTurns();
            checkConditionsForTurns(userInput);
            return userInput;
        }catch(Exception e) {
            return askTotalTurnsException();
        }
    }

    public static void checkConditionsForTurns(int userInput) {
        if (userInput <= 0) {throw new IllegalArgumentException();}
    }

    public static int askAndReceiveTurns() {
        System.out.println("시도할 횟수는 몇회인가요?: ");
        Scanner reader = new Scanner(System.in);
        int userInput = reader.nextInt();
        return userInput;
    }
}
