import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 4/16/2014
 * Time: 4:55 PM
 */
public class Test1 {

    public static final String OS = getOSName();

    public static String getOSName() {
        return System.getProperty("os.name");
    }

    public static void main(String[] args) {
        System.out.println((short)333);

        long t1 = 333L;
        Integer t2 = 333;
        System.out.println(t2.equals(t1));
    }

}
