package RMI;

import Data.Phone;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PhoneManager extends Remote {
    public ArrayList<Phone> getPhonesWithExceededCityCalls(int limit) throws RemoteException;
    public ArrayList<Phone> getPhonesUsedInternationalCalls() throws RemoteException;
    public ArrayList<Phone> getPhonesSortedBySurname() throws RemoteException;
}
