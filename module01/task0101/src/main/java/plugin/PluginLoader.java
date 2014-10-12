package plugin;

import org.apache.log4j.Logger;
import classloader.CustomClassLoader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/7/2014
 * Time: 7:34 AM
 */
public class PluginLoader {

    private final static Logger LOG = Logger.getLogger(PluginLoader.class);

    private CustomClassLoader classLoader;

    public PluginLoader(CustomClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public Boolean loadPlugin(File file) throws IOException {
        LOG.info("Loading classes from " + file.toURI());
        String jarFileName = "jar:" + file.toURI().toURL().toString() + "!/";
        classLoader.addURL(new URL(jarFileName));
        loadJar(new JarFile(file));
        return true;
    }

    private void loadJar(JarFile file) {
        LOG.info("Loading plugin is started...");
        Enumeration<JarEntry> jarContent = file.entries();
        while (jarContent.hasMoreElements()) {
            JarEntry jarEntry = jarContent.nextElement();
            if (!jarEntry.isDirectory() && jarEntry.getName().endsWith(".class")) {
                String className = jarEntry.getName()
                        .replaceAll("/", ".")
                        .replace(".class", "");
                LOG.info("Loading class " + className + "...");
                try {
                    classLoader.loadClass(className);
                } catch (ClassNotFoundException e) {
                    LOG.info("Error while loading class " + className, e);
                }
                LOG.info(className + " loaded.");
            }
        }
        LOG.info("Loading classes finished.");
    }

}
