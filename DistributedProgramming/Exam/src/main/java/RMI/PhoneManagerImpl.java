package RMI;

import Data.Phone;
import Data.PhoneDB;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PhoneManagerImpl extends UnicastRemoteObject implements PhoneManager{
    private final PhoneDB phoneDB;

    public PhoneManagerImpl() throws RemoteException {
        super();
        this.phoneDB = new PhoneDB();
        phoneDB.fillDB();
    }

    public ArrayList<Phone> getPhonesWithExceededCityCalls(int limit) {
        return phoneDB.getPhonesWithExceededCityCalls(limit);
    }

    public ArrayList<Phone> getPhonesUsedInternationalCalls() {
        return phoneDB.getPhonesUsedInternationalCalls();
    }

    public ArrayList<Phone> getPhonesSortedBySurname() {
        return phoneDB.getPhonesSortedBySurname();
    }

}
