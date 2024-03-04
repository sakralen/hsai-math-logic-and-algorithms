package edu.hsai.regexfsm;

import java.util.Scanner;

public class RegexFsmApp {
    private static final RegexFsm automata = new RegexFsm();
    private static final Scanner scanner = new Scanner(System.in);
    private static final int genCount = 10;
    private static final String menu = """
            +------------------------------+
             Ввести строку для проверки [1]
             Сгенерировать строку       [2]
            +------------------------------+""";

    private static final String incorrectInputString = "Некорректный ввод!";

    public static void main(String[] args) {
        do {
            System.out.println(menu);
            System.out.println();

            String input = getMenuChoice();
            switch (input) {
                case "1" -> execAnalyzer();
                case "2" -> execGenerator();
                default -> {
                    System.out.println("Что-то пошло не так...");
                    System.exit(1);
                }
            }
            System.out.println();
        } while (isContinue());
    }

    private static String getMenuChoice() {
        while (true) {
            System.out.println("Выберите пункт меню: (default: 2)");
            String input = scanner.nextLine().trim();
            System.out.println();

            if (input.isEmpty()) {
                return "2";
            }

            if (input.equals("1") || input.equals("2")) {
                return input;
            }

            System.out.println(incorrectInputString);
            System.out.println();
        }
    }

    private static void execAnalyzer() {
        System.out.println("Введите строку:");
        String line = scanner.nextLine();
        System.out.println();

        Analyzer.Result result = Analyzer.analyze(automata, line);

        switch (result) {
            case MATCH -> System.out.println("Строка соответствует регулярному выражению!");
            case NOT_MATCH -> System.out.println("Строка не соответствует регулярному выражению!");
            case NOT_IN_ALPHABET -> System.out.println("Присутствует символ, не принадлежащий алфавиту!");
        }
    }

    private static void execGenerator() {
        for (int i = 0; i < genCount; i++) {
            System.out.println(Generator.generate(automata));
        }
    }

    private static boolean isContinue() {
        while (true) {
            System.out.println("Продолжить? Y/N (default: Y)");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                return true;
            }

            if (input.equals("Y") || input.equals("y")) {
                return true;
            }

            if (input.equals("N") || input.equals("n")) {
                return false;
            }

            System.out.println(incorrectInputString);
            System.out.println();
        }
    }
}
