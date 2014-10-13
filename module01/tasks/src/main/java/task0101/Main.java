package task0101;

import org.apache.log4j.Logger;
import application.MainApplication;

import java.io.IOException;

public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class);

    public static final String WELCOME_MESSAGE = "Programs was started.";
    public static final String EXIT_MESSAGE = "Exiting from program.";

    public static void main(String[] args) throws IOException {
        LOG.info(WELCOME_MESSAGE);

        MainApplication mainApplication = new MainApplication(args);

        Boolean isProgramRunning = true;
        while (isProgramRunning) {
            isProgramRunning = mainApplication.start();
        }

        LOG.info(EXIT_MESSAGE);

    }

}