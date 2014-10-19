package task04;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/19/2014
 * Time: 3:27 PM
 */
public class MyClassLoader extends ClassLoader {

          public void defineClass(byte[] bytes, String name) {
            super.defineClass(name, bytes, 0, bytes.length) ;
          }

}
