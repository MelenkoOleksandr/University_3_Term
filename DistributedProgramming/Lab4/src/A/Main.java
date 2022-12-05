package A;

import java.util.Objects;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Database database = new Database();

        Thread worker1 = new Thread(() -> {
            while (!Thread.interrupted()) {
                database.lockRead();
                String name = "Oleksandr";
                System.out.println("Searching phone by name " + name);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                database.getAccounts().stream().filter(account -> Objects.equals(account.name, name)).forEach(account -> System.out.println("Founded phone " + account.phone + " by name " + name ));
                database.unlockRead();
            }
        });

        Thread worker2 = new Thread(() -> {
            while (!Thread.interrupted()) {
                database.lockRead();
                String phone = "0993606502";
                System.out.println("Searching account by phone " + phone);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                database.getAccounts().stream().filter(account -> Objects.equals(account.phone, phone)).forEach(account -> System.out.println("Founded account  "  +account.name + " " + account.surname + " by phone " + phone ));
                database.unlockRead();
            }
        });

        Thread worker3 = new Thread(() -> {
            while (!Thread.interrupted()) {
                database.lockWrite();

                Account account = new Account("Name1", "Surname1", "02312392131");
                System.out.println("Adding new account...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                database.addAccount(account);
                System.out.println(account + " was added to db");

                System.out.println("Deleting account...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                database.deleteAccount(account);
                System.out.println("Lab1.Account was deleted");
                database.unlockWrite();

            }
        });

        worker1.join();
        worker2.join();
        worker3.join();


        worker2.start();
        worker3.start();
        worker1.start();
    }
}
