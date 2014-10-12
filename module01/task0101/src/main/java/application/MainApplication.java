package application;

import classloader.CustomClassLoader;
import classloader.JarClassLoader;
import file.JarFilesExtractor;
import menu.Menu;
import org.apache.log4j.Logger;
import plugin.Caller;
import plugin.PluginLoader;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MainApplication {

    private final static Logger LOG = Logger.getLogger(MainApplication.class);

    private final static Scanner CONSOLE = new Scanner(System.in);

    private static final Menu CHANGE_FOLDER_MENU = new Menu(CONSOLE, "Choose another folder");

    private String[] args;
    private CustomClassLoader classLoader;
    private JarFilesExtractor jarFilesExtractor;
    private PluginLoader pluginLoader;

    public MainApplication(String[] args) {
        this.args = args;

        jarFilesExtractor = new JarFilesExtractor();
        classLoader = new JarClassLoader();
        pluginLoader = new PluginLoader(classLoader);
    }

    public Boolean start(CustomClassLoader classLoader) throws IOException {
        this.classLoader = classLoader;
        return start();
    }

    public Boolean start() throws IOException {
        String directoryPath = extractDirectoryPath(args);

        List<File> jars = getListOfJars(directoryPath);

        if (jars.isEmpty()) {
            return false;
        }

        Menu selectFileMenu = new Menu(CONSOLE);
        for (File jar : jars) {
            selectFileMenu.addOption(jar.getName());
        }
        System.out.println("Please, choose file that you're going to load:");
        Integer selectedFileIndex = selectFileMenu.getUserInput();

        if (!selectedFileIndex.equals(Menu.EXIT_OPTION_CODE)) {
            pluginLoader.loadPlugin(jars.get(selectedFileIndex - 1));
            callNewFunctionality();
        }

        return false;
    }

    private void callNewFunctionality() {
        print("Please, type in class that you're going to use:");
        String className = CONSOLE.nextLine();
        print("Please, type in method that you're going to use:");
        String methodName = CONSOLE.nextLine();
        Caller caller = new Caller();
        caller.call(className, methodName, classLoader);
    }

    private String extractDirectoryPath(String[] args) {
        LOG.info("Getting path to plugins-directory...");
        if (args.length > 0) {
            LOG.info("Getting path from arguments. Path: " + args[0]);
            return args[0];
        } else {
            String homeDirectoryPath = System.getProperty("user.home");
            LOG.info("Getting path from system. Path: " + homeDirectoryPath);
            return homeDirectoryPath;
        }
    }

    private List<File> getListOfJars(String directoryPath) {
        LOG.info("Retrieving jar files from directory '" + directoryPath + "'");
        List<File> jars = jarFilesExtractor.getJarFiles(directoryPath);
        if (jars.isEmpty()) {
            return handleEmptyFolder();
        } else {
            return jars;
        }
    }

    private List<File> handleEmptyFolder() {
        print("The specified folder is empty or doesn't exist.. Please, choose another folder.");
        Integer userSelectedOption = CHANGE_FOLDER_MENU.getUserInput();
        if (userSelectedOption.equals(Menu.EXIT_OPTION_CODE)) {
            return Collections.emptyList();
        } else {
            System.out.println("Please, enter new directory:");
            String newDirectoryPath = CONSOLE.nextLine();
            return getListOfJars(newDirectoryPath);
        }
    }

    public void print(String message) {
        System.out.println(message);
    }

}
