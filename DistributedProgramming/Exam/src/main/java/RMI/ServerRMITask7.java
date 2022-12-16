package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMITask7 {
    public static void main(String[] args) {
        try {
            PhoneManagerImpl phoneManagerImpl = new PhoneManagerImpl();
            Registry registry = LocateRegistry.createRegistry(123);
            registry.rebind("PhoneManager", phoneManagerImpl);
            System.out.println("Server started!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
