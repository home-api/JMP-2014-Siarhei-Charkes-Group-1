package classloader;

import java.net.URL;

public interface CustomClassLoader {

    void addURL(URL url);

    Class<?> loadClass(String className) throws ClassNotFoundException;

}
