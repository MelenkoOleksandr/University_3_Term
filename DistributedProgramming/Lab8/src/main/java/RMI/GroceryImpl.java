package RMI;

import DAO.GroceryDAO;
import Grocery.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroceryImpl extends UnicastRemoteObject implements Grocery{
    private final GroceryDAO grocery;

    public GroceryImpl() throws RemoteException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        grocery = new GroceryDAO();
    }

    @Override
    public void addNewCategory(String name) throws RemoteException {
        synchronized (grocery) {
            grocery.addProductCategory(name);
        }

    }

    @Override
    public void deleteCategory(int categoryId) throws RemoteException {
        synchronized (grocery) {
            grocery.deleteProductCategory(categoryId);
        }
    }

    @Override
    public void addProductToCategory(int categoryId, String productName, int price, int amount) throws RemoteException {
        synchronized (grocery) {
            grocery.addProduct(categoryId, productName, price, amount);
        }
    }

    @Override
    public void deleteProduct(int productId) throws RemoteException {
        synchronized (grocery) {
            grocery.deleteProduct(productId);
        }
    }

    @Override
    public void editProduct(int productId, int categoryId, String productName, int price, int amount) throws RemoteException {
        synchronized (grocery) {
            grocery.updateProduct(productId, categoryId, productName, price, amount);
        }
    }

    @Override
    public int countProductsInCategory(int categoryId) throws RemoteException {
        synchronized (grocery) {
            return grocery.countProductsInCategory(categoryId);
        }
    }

    @Override
    public Product searchProductByName(String name) throws RemoteException {
        synchronized (grocery) {
            return grocery.searchProductByName(name);
        }
    }

    @Override
    public ArrayList<Product> getAllProductsInCategory(int categoryId) throws RemoteException {
        synchronized (grocery) {
            return grocery.getAllProductsInCategory(categoryId);
        }
    }

    @Override
    public ArrayList<ProductCategory> getAllCategories() throws RemoteException {
        synchronized (grocery) {
            return grocery.getAllCategories();
        }
    }

    @Override
    public void stop() throws RemoteException {
        System.exit(0);
    }
}
