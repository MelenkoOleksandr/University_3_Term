import java.sql.SQLException;
import java.util.Scanner;

public class GroceryManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/grocery";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static void showMenu() {
        System.out.println("1. Show all product categories");
        System.out.println("2. Show all products");
        System.out.println("3. Show all products in a category");
        System.out.println("4. Add a new product category");
        System.out.println("5. Add a new product");
        System.out.println("6. Update a product category");
        System.out.println("7. Update a product");
        System.out.println("8. Delete a product category");
        System.out.println("9. Delete a product");
        System.out.println("10. Exit");
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Grocery grocery = new Grocery(DB_URL, DB_USER, DB_PASSWORD);
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        while (true) {
            showMenu();
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    grocery.showAllProductCategories();
                    break;
                case "2":
                    grocery.showAllProducts();
                    break;
                case "3":
                    System.out.println("Enter category ID: ");
                    int categoryId = Integer.parseInt(scanner.nextLine());
                    grocery.showAllProductsInCategory(categoryId);
                    break;
                case "4":
                    System.out.println("Enter category name: ");
                    String newCategoryName = scanner.nextLine();
                    grocery.addProductCategory(newCategoryName);
                    break;
                case "5":
                    System.out.println("Enter product name: ");
                    String newProductName = scanner.nextLine();
                    System.out.println("Enter product price: ");
                    int newProductPrice = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter product quantity: ");
                    int newProductQuantity = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter product category ID: ");
                    int newProductCategoryId = Integer.parseInt(scanner.nextLine());
                    grocery.addProduct(newProductCategoryId, newProductName, newProductPrice, newProductQuantity);
                    break;
                case "6":
                    System.out.println("Enter category ID: ");
                    int categoryIdToUpdate = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter new category name: ");
                    String newCategoryNameToUpdate = scanner.nextLine();
                    grocery.updateProductCategory(categoryIdToUpdate, newCategoryNameToUpdate);
                    break;
                case "7":
                    System.out.println("Enter product ID: ");
                    int productIdToUpdate = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter new product name: ");
                    String newProductNameToUpdate = scanner.nextLine();
                    System.out.println("Enter new product price: ");
                    int newProductPriceToUpdate = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter new product quantity: ");
                    int newProductQuantityToUpdate = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter new product category ID: ");
                    int newProductCategoryIdToUpdate = Integer.parseInt(scanner.nextLine());
                    grocery.updateProduct(productIdToUpdate,newProductCategoryIdToUpdate, newProductNameToUpdate, newProductPriceToUpdate, newProductQuantityToUpdate);
                    break;
                case "8":
                    System.out.println("Enter category ID: ");
                    int categoryIdToDelete = Integer.parseInt(scanner.nextLine());
                    grocery.deleteProductCategory(categoryIdToDelete);
                    break;
                case "9":
                    System.out.println("Enter product ID: ");
                    int productIdToDelete = Integer.parseInt(scanner.nextLine());
                    grocery.deleteProduct(productIdToDelete);
                    break;
                case "10":
                    grocery.stop();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
