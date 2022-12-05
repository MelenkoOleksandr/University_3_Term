package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Grocery.*;

public interface Grocery extends Remote {
    public void addNewCategory(String name) throws RemoteException;
    public void deleteCategory(int categoryId) throws RemoteException;
    public void addProductToCategory(int categoryId, String productName, int price, int amount) throws RemoteException;
    public void deleteProduct(int productId) throws RemoteException;
    public void editProduct(int productId, int categoryId, String productName, int price, int amount) throws RemoteException;
    public int countProductsInCategory(int categoryId) throws RemoteException;
    public Product searchProductByName(String name) throws RemoteException;
    public ArrayList<Product> getAllProductsInCategory(int categoryId) throws RemoteException;
    public ArrayList<ProductCategory> getAllCategories() throws RemoteException;

    public void stop() throws RemoteException;
}
