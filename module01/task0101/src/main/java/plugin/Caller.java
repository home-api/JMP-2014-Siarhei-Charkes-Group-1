package plugin;

import org.apache.log4j.Logger;
import classloader.CustomClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/7/2014
 * Time: 12:20 PM
 */
public class Caller {

    private final static Logger LOG = Logger.getLogger(Caller.class);

    public void call(String className, String methodName, CustomClassLoader classLoader) {
        LOG.info("Calling method " + methodName + " of the class " + className);
        try {
            Class clazz = classLoader.loadClass(className);
            Method method = clazz.getMethod(methodName);
            method.invoke(clazz.newInstance());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        LOG.info("Method was called.");
    }
}
