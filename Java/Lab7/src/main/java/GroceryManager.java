import javax.xml.transform.TransformerException;
import java.util.ArrayList;
import java.util.Scanner;

public class GroceryManager {
    static Scanner scanner = new Scanner(System.in);
    static Grocery grocery = new Grocery();

    public static void showMenu() {
        System.out.println("1. Load data from XML");
        System.out.println("2. Save data to XML");
        System.out.println("3. Show all product categories");
        System.out.println("4. Show all products");
        System.out.println("5. Show all products in a category");
        System.out.println("6. Add a new product category");
        System.out.println("7. Add a new product");
        System.out.println("8. Update a product category");
        System.out.println("9. Update a product");
        System.out.println("10. Delete a product category");
        System.out.println("11. Delete a product");
        System.out.println("12. Exit");
    }

    public static void main(String[] args) throws TransformerException {
        String choice;
        while (true) {
            showMenu();
            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> grocery.loadDataFromXML();
                case "2" -> grocery.saveData();
                case "3" -> grocery.showAllProductCategories();
                case "4" -> grocery.showAllProducts();
                case "5" -> {
                    System.out.println("Enter category ID: ");
                    int categoryId = Integer.parseInt(scanner.nextLine());
                    grocery.showAllProductsInCategory(categoryId);
                }
                case "6" -> {
                    System.out.println("Enter category ID: ");
                    int newCategoryId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter category name: ");
                    String newCategoryName = scanner.nextLine();
                    grocery.addProductCategory(new ProductCategory(newCategoryId, newCategoryName));
                }
                case "7" -> {
                    System.out.println("Enter product ID: ");
                    int newProductId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter product name: ");
                    String newProductName = scanner.nextLine();
                    System.out.println("Enter product price: ");
                    int newProductPrice = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter product quantity: ");
                    int newProductQuantity = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter product category ID: ");
                    int newProductCategoryId = Integer.parseInt(scanner.nextLine());
                    grocery.addProduct(newProductCategoryId, new Product(newProductId, newProductName, newProductPrice, newProductQuantity));
                }
                case "8" -> {
                    System.out.println("Enter category ID: ");
                    int categoryIdToUpdate = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter new category name: ");
                    String newCategoryNameToUpdate = scanner.nextLine();
                    grocery.updateProductCategory(categoryIdToUpdate, newCategoryNameToUpdate);
                }
                case "9" -> {
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
                    grocery.updateProduct(productIdToUpdate, productIdToUpdate, new Product(productIdToUpdate, newProductNameToUpdate, newProductPriceToUpdate, newProductQuantityToUpdate));
                }
                case "10" -> {
                    System.out.println("Enter category ID: ");
                    int categoryIdToDelete = Integer.parseInt(scanner.nextLine());
                    grocery.deleteProductCategory(categoryIdToDelete);
                }
                case "11" -> {
                    System.out.println("Enter product ID: ");
                    int productIdToDelete = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter product category ID: ");
                    int productCategoryIdToDelete = Integer.parseInt(scanner.nextLine());
                    grocery.deleteProduct(productCategoryIdToDelete, productIdToDelete);
                }
                case "12" -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
