package task0101.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/5/2014
 * Time: 12:26 PM
 */
public class Menu {

    public static final int EXIT_OPTION_CODE = 0;
    public static final String EXIT_OPTION_TEXT = "Exit from program";

    public static final String INCORRECT_SELECTION_MESSAGE =
            "You have entered incorrect value. Please choose the right one.";

    private Scanner console;

    private Map<Integer, String> options;

    public Menu() {
        options = new HashMap<Integer, String>();
        options.put(EXIT_OPTION_CODE, EXIT_OPTION_TEXT);
    }

    public Menu(Scanner console, String... optionValues) {
        this();

        this.console = console;

        for (int i = 0; i < optionValues.length; i++) {
            options.put(i + 1, optionValues[i]);
        }
    }

    public void addOption(String option) {
        options.put(options.size(), option);
    }

    public Integer getUserInput() {
        for (Map.Entry<Integer, String> option : options.entrySet()) {
            System.out.println(option.getKey() + ": " + option.getValue() + ".");
        }

        String userInputString = console.nextLine();
        while (!verifyUserInput(userInputString)) {
            System.out.println(INCORRECT_SELECTION_MESSAGE);
            userInputString = console.nextLine();
        }

        return Integer.valueOf(userInputString);
    }

    private boolean verifyUserInput(String userInput) {
        try {
            Integer userChoice = Integer.valueOf(userInput);
            return userChoice >= 0 && userChoice <= options.size();
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
