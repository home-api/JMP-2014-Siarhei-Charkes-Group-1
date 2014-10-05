package task0101;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/5/2014
 * Time: 1:20 PM
 */
public class CustomClassLoader extends URLClassLoader {
    public CustomClassLoader(URL[] urls) {
        super(urls);
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }
}
