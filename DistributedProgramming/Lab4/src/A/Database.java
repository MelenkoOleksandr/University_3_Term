package A;

import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database {
    ArrayList<Account> accounts = new ArrayList<>();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public Database(){
        fillDB();
    }

    public void fillDB() {
        accounts.add(new Account("Oleksandr", "Melenko", "0993606502"));
        accounts.add(new Account("Kiril", "Zaharenko", "0502329323"));
        accounts.add(new Account("Polina", "Bondarevska", "0993231424"));
        accounts.add(new Account("Kurulo", "Polojenets", "04324535"));
    }

    public void lockRead() {
        readWriteLock.readLock().lock();
    }

    public void unlockRead() {
        readWriteLock.readLock().unlock();
    }

    public void lockWrite() {
        readWriteLock.writeLock().lock();
    }

    public void unlockWrite() {
        readWriteLock.writeLock().unlock();
    }

    public void deleteAccount(Account account) {
        accounts.remove(account);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
