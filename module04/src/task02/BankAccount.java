package task02;

public class BankAccount {

    private int amount = 0;

    public BankAccount(int initialAmount) {
        amount = initialAmount;
    }

    public synchronized int getAmount(int processTime) throws InterruptedException {
        Thread.sleep(processTime);
        return amount;
    }

    public synchronized void changeAmount(int diff, int processTime) throws InterruptedException {
        Thread.sleep(processTime);
        amount += diff;
    }

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(0);

        Thread banker1 = new Thread(new Banker(bankAccount, 500));
        Thread banker2 = new Thread(new Banker(bankAccount, 400));
        Thread client1 = new Thread(new Client(bankAccount, 400));
        Thread client2 = new Thread(new Client(bankAccount, 500));

        // The real-life example begins here
        try {
            // banker1 begins the changing of the bank account
            banker1.start();
            // a bit later client1 comes and wants to know the balance
            // But this client can not to get the balance because it's changed now
            client1.start();
            Thread.sleep(2000);

            // Now client comes and want to know the balance
            // but this operation could take a bit long time
            client2.start();
            // at the same moment the banker tries to change the balance
            // but he can not because client is viewing the balance
            banker2.start();

            /*
            Expected result: 100 100
            If you delete synchronized, result will be 0 200 - it's incorrect result
             */
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static class Banker implements Runnable {
        private BankAccount bankAccount;
        private int processTime;

        private Banker(BankAccount bankAccount, int processTime) {
            this.bankAccount = bankAccount;
            this.processTime = processTime;
        }

        @Override
        public void run() {
            try {
                bankAccount.changeAmount(100, processTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Client implements Runnable {
        private BankAccount bankAccount;
        private int processTime;

        private Client(BankAccount bankAccount, int processTime) {
            this.bankAccount = bankAccount;
            this.processTime = processTime;
        }

        @Override
        public void run() {
            try {
                System.out.println(bankAccount.getAmount(processTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}