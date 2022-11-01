package groceryXML;

import java.util.ArrayList;

public class ProductCategory {
    public int categoryId;
    public String categoryName;
    public ArrayList<Product> products;

    public ProductCategory(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.products = new ArrayList<Product>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public Product getProduct(int productCode) {
        return products.get(productCode);
    }

    public void deleteProduct(int productCode) {
        products.remove(productCode);
    }

    public void updateProduct(int productCode, Product product) {
        products.set(productCode, product);
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String toString() {
        return "Category ID: " + categoryId + ", Category Name: " + categoryName;
    }
}
