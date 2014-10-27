package task03;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/26/2014
 * Time: 4:41 PM
 */
public class Calculation {

    public static void main(String[] args) throws InterruptedException {
        Thread longThread = new Thread(new FibonacciCalculator());
        longThread.start();

        Thread.sleep(2000);
        longThread.interrupt();
    }

    private static class FibonacciCalculator implements Runnable {
        private Long n2 = 0L;
        private Long n1 = 0L;

        @Override
        public void run() {
            while (n2 < 999999999999999999L) {
                if (Thread.interrupted()) {
                    return;
                }
                Long newN1 = n2 + n1;
                n1 = n2;
                n2 = newN1;
            }
        }
    }

}
