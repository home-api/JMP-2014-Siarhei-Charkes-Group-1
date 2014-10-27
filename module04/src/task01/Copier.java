package task01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/26/2014
 * Time: 4:57 PM
 */
public class Copier {

    private static BlockingQueue<Integer> buffer = new SynchronousQueue<Integer>();

    private static List<String> exceptions = Collections.synchronizedList(new ArrayList<String>());

    private static long fromFileSize = 0l;
    private static File toFile;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the input file name:");
        String inputFileName = scanner.nextLine();

        System.out.println("Please enter the output file name:");
        String outputFileName = scanner.nextLine();

        File fromFile = new File(inputFileName);
        fromFileSize = fromFile.length();
        FileReader fileReader = new FileReader(fromFile);
        toFile = new File(outputFileName);
        FileWriter fileWriter = new FileWriter(toFile);

        new Thread(fileReader).start();
        new Thread(fileWriter).start();

        if (!exceptions.isEmpty()) {
            printAllEceptions();
        }
    }

    private static void printAllEceptions() {
        System.out.println("Something went wrong...");
        for (String exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static class FileReader implements Runnable {

        private File file;

        private FileReader(File file) {
            this.file = file;
        }

        @Override
        public void run() {
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);
                int c;
                while ((c = inputStream.read()) != -1) {
                    buffer.put(c);
                }
            } catch (Exception e) {
                exceptions.add(e.getMessage());
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException ex) {
                    exceptions.add(ex.getMessage());
                }
            }
        }
    }

    private static class FileWriter implements Runnable {

        private File file;

        private FileWriter(File file) {
            this.file = file;
        }

        @Override
        public void run() {
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
                while (toFile.length() != fromFileSize) {
                    outputStream.write(buffer.take());
                    outputStream.flush();
                }
            } catch (Exception e) {
                exceptions.add(e.getMessage());
            } finally {
                try {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException ex) {
                    exceptions.add(ex.getMessage());
                }
            }
        }
    }

}
