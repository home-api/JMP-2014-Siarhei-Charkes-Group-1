package task0101.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/7/2014
 * Time: 9:36 AM
 */
public class JarFilesExtractor {

    public List<File> getJarFiles(String directoryPath) {
        File directory = new File(directoryPath);
        boolean isDirectory = directory.exists() && directory.isDirectory();
        return isDirectory ? getJarFiles(directory) : new ArrayList<File>();
    }

    private List<File> getJarFiles(File directory) {
        List<File> jarFiles = new ArrayList<File>();
        //noinspection ConstantConditions
        for (File file : directory.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".jar")) {
                jarFiles.add(file);
            }
        }
        return jarFiles;
    }
}
