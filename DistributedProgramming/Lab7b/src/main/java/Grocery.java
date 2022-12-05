import java.sql.*;
import java.util.ArrayList;

public class Grocery {
    Statement statement = null;
    Connection connection = null;

    public Grocery(String url, String user, String password) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(url, user, password);
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

    public void updateProductCategory(int categoryId, String categoryName) {
        try {
            String query = "UPDATE grocery.productcategories SET categoryName = '" + categoryName + "' WHERE id = " + categoryId;
            statement.executeUpdate(query);
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
