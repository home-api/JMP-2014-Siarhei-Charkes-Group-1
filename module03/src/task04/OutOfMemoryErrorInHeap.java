package task04;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryErrorInHeap {

    private static List<Object> list = new ArrayList<Object>();

    public static void main(String[] args) {

        while (true) {
            list.add(new byte[1111111]);
            System.out.println(Runtime.getRuntime().freeMemory());
        }
    }

}
