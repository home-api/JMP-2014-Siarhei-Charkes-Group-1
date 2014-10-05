package task0101;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/5/2014
 * Time: 12:26 PM
 */
public class Menu {

    public static final int EXIT_OPTION_CODE = 0;
    public static final String EXIT_OPTION_TEXT = "Exit from program";

    private Map<Integer, String> options;

    public Menu() {
        options = new HashMap<Integer, String>();
        options.put(EXIT_OPTION_CODE, EXIT_OPTION_TEXT);
    }

    public Menu(List<String> optionValues) {
        this();

        for (int i = 0; i < optionValues.size(); i++) {
            options.put(i + 1, optionValues.get(i));
        }
    }

    public void addOption(String option) {
        options.put(options.size() - 1, option);
    }

    public void showMenu() {
        for (Map.Entry<Integer, String> option : options.entrySet()) {
            System.out.println(option.getKey() + ": " + option.getValue() + ".");
        }
    }

    public int size() {
        return options.size() - 1;
    }

}
