package task02;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BankAccount {

    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private int amount = 0;

    public BankAccount(int initialAmount) {
        amount = initialAmount;
    }

    public int getAmount(int processTime) throws InterruptedException {
        readWriteLock.readLock().lock();
        Thread.sleep(processTime);
        readWriteLock.readLock().unlock();
        return amount;
    }

    public void changeAmount(int diff, int processTime) throws InterruptedException {
        readWriteLock.writeLock().lock();
        Thread.sleep(processTime);
        amount += diff;
        readWriteLock.writeLock().unlock();
    }

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(0);

        Thread banker1 = new Thread(new Banker(bankAccount, 300));
        Thread banker3 = new Thread(new Banker(bankAccount, 500));
        Thread banker2 = new Thread(new Banker(bankAccount, 400));
        Thread client1 = new Thread(new Client(bankAccount, 1000));
        Thread client2 = new Thread(new Client(bankAccount, 500));
        Thread client3 = new Thread(new Client(bankAccount, 1000));
        Thread client4 = new Thread(new Client(bankAccount, 1000));
        Thread client5 = new Thread(new Client(bankAccount, 1000));

        // The real-life example begins here
        try {
            banker1.start();
            client1.start();
            client3.start();
            client4.start();
            Thread.sleep(2000);

            banker3.start();
            banker2.start();
            client2.start();
            client5.start();

            /*
            Expected result: 100 100 100 200 300
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