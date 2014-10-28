package task01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/26/2014
 * Time: 4:57 PM
 */
public class Copier {

    public static final int MAX_QUEUE_SIZE = 100;

    private BlockingQueue<String> buffer = new LinkedBlockingQueue<String>(MAX_QUEUE_SIZE);

    private List<String> exceptions = Collections.synchronizedList(new ArrayList<String>());

    private long fromFileSize;
    private File toFile;

    public List<String> copy(File from, File to) {
        fromFileSize = from.length();
        toFile = to;

        Reader fileReader = new Reader(from);
        Writer fileWriter = new Writer(to);

        Thread readerThread = new Thread(fileReader);
        Thread writerThread = new Thread(fileWriter);

        readerThread.start();
        writerThread.start();

        try {
            readerThread.join();
            writerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return exceptions;
    }


    private class Reader implements Runnable {

        private File file;

        private Reader(File file) {
            this.file = file;
        }

        @Override
        public void run() {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.put(line);
                }
            } catch (Exception e) {
                exceptions.add(e.getMessage());
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException ex) {
                    exceptions.add(ex.getMessage());
                }
            }
        }
    }

    private class Writer implements Runnable {

        private File file;

        private Writer(File file) {
            this.file = file;
        }

        @Override
        public void run() {
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(file));
                while (toFile.length() < fromFileSize) {
                    writer.write(buffer.poll(1, TimeUnit.SECONDS));
                    writer.newLine();
                }
            } catch (Exception e) {
                exceptions.add(e.getMessage());
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
