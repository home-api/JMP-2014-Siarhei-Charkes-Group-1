package task0102;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class);
    private static final Scanner CONSOLE = new Scanner(System.in);

    private static final Menu SELECT_FILE_MENU = new Menu(Arrays.asList("Choose another folder"));

    public static final String NO_PLUGINS_MESSAGE =
            "There are no plugins for download.\nYou can select another folder or exit.";
    public static final String INCORRECT_SELECTION_MESSAGE =
            "You have entered incorrect value. Please choose the right one.";
    public static final String EXIT_MESSAGE = "Bye-bye...";
    public static final String WELCOME_MESSAGE =
            "You are about to load new functionality. Please choose file which you wont to load.";

    public static void main(String[] args) {
        print(WELCOME_MESSAGE);

        String directoryPath = extractDirectoryPath(args);
        List<File> existingPlugins = getExistingPlugins(directoryPath);

        Menu createFileMenu = createSelectFileMenu(existingPlugins);
        createFileMenu.showMenu();

        String userInputString = CONSOLE.nextLine();
        while (!verifyUserInput(userInputString)
                || Integer.parseInt(userInputString) > createFileMenu.size()) {
            print(INCORRECT_SELECTION_MESSAGE);
            createFileMenu.showMenu();
            userInputString = CONSOLE.nextLine();
        }

        Integer userChoice = Integer.parseInt(userInputString);
        if (userChoice.equals(Menu.EXIT_OPTION_CODE)) {
            print(EXIT_MESSAGE);
            exit();
        } else {
            CustomClassLoader classLoader = new CustomClassLoader(new URL[]{});
            loadPlugin(existingPlugins.get(userChoice - 1), classLoader);
            makeCall(classLoader);
            main(args);
        }
    }

    private static String extractDirectoryPath(String[] args) {
        LOG.info("Getting path to directory...");
        if (args.length > 0) {
            LOG.info("Getting path from arguments. Path: " + args[0]);
            return args[0];
        } else {
            String homeDirectoryPath = System.getProperty("user.home");
            LOG.info("Getting path from system. Path: " + homeDirectoryPath);
            return homeDirectoryPath;
        }
    }

    private static List<File> getExistingPlugins(String directoryPath) {
        List<File> existingPlugins;

        File pluginDirectory = new File(directoryPath);
        if (!pluginDirectory.exists()) {
            print("This folder doesn't exist.");
            SELECT_FILE_MENU.showMenu();
            String userInputString = getUserDirectorySelection();
            getExistingPlugins(userInputString);
        }

        existingPlugins = getJarFiles(pluginDirectory);

        if (existingPlugins.isEmpty()) {
            print(NO_PLUGINS_MESSAGE);
            String userInputString = getUserDirectorySelection();
            getExistingPlugins(userInputString);
        }

        return existingPlugins;
    }

    private static String getUserDirectorySelection() {
        String userInputString = CONSOLE.nextLine();
        while (!verifyUserInput(userInputString)
                || Integer.valueOf(userInputString) > SELECT_FILE_MENU.size()) {
            print(INCORRECT_SELECTION_MESSAGE);
            SELECT_FILE_MENU.showMenu();
            userInputString = CONSOLE.nextLine();
        }

        Integer userChoice = Integer.valueOf(userInputString);
        if (userChoice.equals(Menu.EXIT_OPTION_CODE)) {
            print(EXIT_MESSAGE);
            exit();
            return null;
        } else {
            print("Enter folder that contains plugin(s):");
            return CONSOLE.nextLine();
        }
    }

    private static List<File> getJarFiles(File pluginDirectory) {
        LOG.info("Getting list of jar-file from directory...");
        List<File> existingJarFiles = new ArrayList<File>();

        //noinspection ConstantConditions
        for (File file : pluginDirectory.listFiles()) {
            if (!file.isDirectory() && file.getName().endsWith(".jar")) {
                existingJarFiles.add(file);
            }
        }
        LOG.info("Jar-files were gotten from directory: " + existingJarFiles.size() + " files");
        return existingJarFiles;
    }

    private static Menu createSelectFileMenu(List<File> existingPlugins) {
        Menu selectFileMenu = new Menu();
        for (File file : existingPlugins) {
            selectFileMenu.addOption(file.getName());
        }
        return selectFileMenu;
    }

    private static void loadPlugin(File file, CustomClassLoader classLoader) {
        LOG.info("Loading classes started...");
        try {
            String jarFileName = "jar:" + file.toURI().toURL().toString() + "!/";

            JarFile jarFile = new JarFile(file);
            Enumeration<JarEntry> jarContent = jarFile.entries();
            while (jarContent.hasMoreElements()) {
                JarEntry jarEntry = jarContent.nextElement();
                if (!jarEntry.isDirectory() && jarEntry.getName().endsWith(".class")) {
                    String className = jarEntry.getName()
                            .replaceAll("/", ".")
                            .replace(".class", "");
                    LOG.info("Loading class " + className + "...");
                    try {
                        classLoader.addURL(new URL(jarFileName));
                        Class.forName(className, true, classLoader);
                    } catch (ClassNotFoundException e) {
                        LOG.info("Error while loading class " + className, e);
                    }
                    LOG.info(className + " loaded.");
                    print("loaded " + className);
                }
            }
            LOG.info("Loading classes finished.");
        } catch (IOException e) {
            LOG.info("Error while opening file " + file, e);
        }
    }

    private static void makeCall(ClassLoader classLoader) {
        try {
            print("Enter class name that you are going to use:");
            String className = CONSOLE.nextLine();
            Class loadedClass = classLoader.loadClass(className);

            print(className + " has methods:");
            for (Method method : loadedClass.getMethods()) {
                print(method.getName());
            }

            print("Choose method to call:");
            String methodName = CONSOLE.nextLine();
            //noinspection unchecked
            loadedClass.getMethod(methodName).invoke(loadedClass.newInstance());

        } catch (ClassNotFoundException e) {
            LOG.info(e);
        } catch (InvocationTargetException e) {
            LOG.info(e);
        } catch (NoSuchMethodException e) {
            LOG.info(e);
        } catch (InstantiationException e) {
            LOG.info(e);
        } catch (IllegalAccessException e) {
            LOG.info(e);
        }
    }

    private static boolean verifyUserInput(String userInput) {
        try {
            Integer userChoice = Integer.valueOf(userInput);
            return userChoice >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void print(String message) {
        System.out.println(message);
    }

    private static void exit() {
        System.exit(Menu.EXIT_OPTION_CODE);
    }

}
