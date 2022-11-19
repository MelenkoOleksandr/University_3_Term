package DAO;

import java.sql.*;
import java.util.ArrayList;

import Grocery.Product;
import Grocery.ProductCategory;

public class GroceryDAO {
    private final String DB_URL = "jdbc:mysql://localhost:3306/grocery";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "root";

    Statement statement = null;
    Connection connection = null;

    public GroceryDAO() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        statement = connection.createStatement();
    }

    public void showAllProductCategories() {
        try {
            String query = "SELECT * FROM grocery.productcategories";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("Category ID: " + resultSet.getInt("id") + ", Category Name: " + resultSet.getString("categoryName"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showAllProducts() {
        try {
            String query = "SELECT * FROM grocery.products";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("Product ID: " + resultSet.getInt("id") + ", Product Name: " + resultSet.getString("productName") + ", Amount: " +  resultSet.getInt("amount") + ", Price: " + resultSet.getInt("price"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showAllProductsInCategory(int categoryId) {
        try {
            String query = "SELECT * FROM grocery.products WHERE categoryId = " + categoryId;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("Product ID: " + resultSet.getInt("id") + ", Product Name: " + resultSet.getString("productName") + ", Amount: " +  resultSet.getInt("amount") + ", Price: " + resultSet.getInt("price"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addProduct(int categoryId, String productName, int price, int amount) {
        try {
            String query = "INSERT INTO grocery.products (categoryId, productName, price, amount) VALUES (" + categoryId + ", '" + productName + "', " + price + ", " + amount + ")";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addProductCategory(String categoryName) {
        try {
            String query = "INSERT INTO grocery.productcategories (categoryName) VALUES ('" + categoryName + "')";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(int productId) {
        try {
            String query = "DELETE FROM grocery.products WHERE id = " + productId;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProductCategory(int categoryId) {
        try {
            String query = "DELETE FROM grocery.productcategories WHERE id = " + categoryId;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProduct(int productId, int categoryId, String productName, int price, int amount) {
        try {
            String query = "UPDATE grocery.products SET categoryId = " + categoryId + ", productName = '" + productName + "', price = " + price + ", amount = " + amount + " WHERE id = " + productId;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countProductsInCategory(int categoryId) {
        try {
            String query = "SELECT COUNT(*) FROM grocery.products WHERE categoryId = " + categoryId;
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            int count = resultSet.getInt(1);
            resultSet.close();
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countCategories() {
            try {
                String query = "SELECT COUNT(*) FROM grocery.productcategories";
                ResultSet resultSet = statement.executeQuery(query);
                resultSet.next();
                int count = resultSet.getInt(1);
                resultSet.close();
                return count;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    public Product searchProductByName(String name) {
        try {
            String query = "SELECT * FROM grocery.products WHERE productName = '" + name + "'";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            Product product = new Product(resultSet.getInt("id"), resultSet.getString("productName"), resultSet.getInt("amount"), resultSet.getInt("price"));
            resultSet.close();
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Product> getAllProductsInCategory(int categoryId) {
        try {
            String query = "SELECT * FROM grocery.products WHERE categoryId = " + categoryId;
            ResultSet resultSet = statement.executeQuery(query);
            ArrayList<Product> products = new ArrayList<Product>();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("id"), resultSet.getString("productName"), resultSet.getInt("amount"), resultSet.getInt("price")));
             }
            resultSet.close();
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ProductCategory> getAllCategories() {
        try {
            String query = "SELECT * FROM grocery.productcategories";
            ResultSet resultSet = statement.executeQuery(query);
            ArrayList<ProductCategory> categories = new ArrayList<ProductCategory>();
            while (resultSet.next()) {
                categories.add(new ProductCategory(resultSet.getInt("id"), resultSet.getString("categoryName")));
            }
            resultSet.close();
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
