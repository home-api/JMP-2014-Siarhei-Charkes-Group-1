package task02;

public class Main {

    public static void main(String[] args) {
        Integer integer  = new Integer(99);
        System.out.println(integer);

        passByReference(integer);
        System.out.println(integer);

        System.out.println("----------------------------------------------");

        integer = new Integer(333);
        System.out.println(integer);

        integer = returnNewObject(integer);
        System.out.println(integer);

        while(true) {

        }
    }

    private static void passByReference(Integer integer) {
        integer = new Integer(111);
        System.out.println("passByReference:" + integer);
    }

    private static Integer returnNewObject(Integer integer) {
        integer = new Integer(999);
        System.out.println("returnNewObject: " + integer);
        return integer;

    }

}
