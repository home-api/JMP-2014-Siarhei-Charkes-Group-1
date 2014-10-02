import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by Yahor_Karabitsyn on 10/2/2014.
 */
public class Main {

    private final static String EXIT = "0";
    private final static String LOAD = "1";

    private final static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        System.out.println("Hello!");
        System.out.println("Choose one of the following options:");
        System.out.println("1: load new functionality");
        System.out.println("0: exit from program");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (userInput.equals(EXIT)) {
                System.out.println("Bye-bye.");
                break;
            } else if (userInput.equals(LOAD)) {
                loadClasses();
            } else {
                System.out.println("You have selected incorrect option.");
            }
        }
    }

    private static void loadClasses() {
        logger.info("loading classes...");
        try {
            URL[] filesToLoad = {new URL("jar:file://../plugins/Test1.jar!/")};
            URLClassLoader classLoader = new URLClassLoader(filesToLoad);
            Class test1 = Class.forName("Test1", true, classLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}