package classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class JarClassLoader extends URLClassLoader implements CustomClassLoader {

    public JarClassLoader() {
        super(new URL[]{});
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

}
