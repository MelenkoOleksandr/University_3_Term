import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class Grocery {
    private final ArrayList<ProductCategory> productCategories= new ArrayList<ProductCategory>();
    DocumentBuilder db = null;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


    public void loadDataFromXML() {
        Document doc = null;
        dbf.setValidating(true);

        try {
            db = dbf.newDocumentBuilder();
            db.setErrorHandler(new SimpleErrorHandler());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


        try {
            doc = db.parse( new File("src\\main\\resources\\grocery.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Element root = doc.getDocumentElement();
        if (root.getNodeName().equals("Grocery")) {
            NodeList productCategories = root.getElementsByTagName("ProductCategory");
            for (int i = 0; i < productCategories.getLength(); i++) {
                Element productCategory = (Element) productCategories.item(i);

                int categoryId = Integer.parseInt(productCategory.getAttribute("id"));
                String categoryName = productCategory.getAttribute("categoryName");
                ProductCategory category = new ProductCategory(categoryId, categoryName);

                NodeList products = productCategory.getElementsByTagName("Product");
                for (int j = 0; j < products.getLength(); j++) {
                    Element product = (Element) products.item(j);
                    Product newProduct = new Product(
                            Integer.parseInt(product.getAttribute("id")),
                            product.getAttribute("productName"),
                            Integer.parseInt(product.getAttribute("price")),
                            Integer.parseInt(product.getAttribute("amount"))
                    );
                    category.addProduct(newProduct);
                }
                this.productCategories.add(category);
            }
        }


    }
    public void saveData() throws TransformerException {
        Document doc = db.newDocument();
        Element root = doc.createElement("Grocery");
        doc.appendChild(root);

        for (ProductCategory category : this.productCategories) {
            Element productCategory = doc.createElement("ProductCategory");
            productCategory.setAttribute("id", String.valueOf(category.getCategoryId()));
            productCategory.setAttribute("categoryName", category.getCategoryName());
            root.appendChild(productCategory);

            for (Product product : category.getProducts()) {
                Element productElement = doc.createElement("Product");
                productElement.setAttribute("id", String.valueOf(product.getProductCode()));
                productElement.setAttribute("productName", product.getProductName());
                productElement.setAttribute("price", String.valueOf(product.getPrice()));
                productElement.setAttribute("amount", String.valueOf(product.getAmount()));
                productCategory.appendChild(productElement);
            }
        }

        Source source = new DOMSource(doc);
        Result result = new StreamResult(new File("src\\main\\resources\\grocery.xml"));
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "Windows-1251");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,  "grocery.dtd" );
        transformer.transform(source, result);
    }

    public void addProductCategory(ProductCategory productCategory) {
        for (ProductCategory category : this.productCategories) {
            if (category.getCategoryId() == productCategory.getCategoryId()) {
                System.out.println("Product category already exists");
                return;
            }
        }
        this.productCategories.add(productCategory);
    }
    public ProductCategory getProductCategory(int categoryId) {
        for (ProductCategory category : this.productCategories) {
            if (category.getCategoryId() == categoryId) {
                return category;
            }
        }
        return null;
    }
    public void updateProductCategory(int categoryId, String categoryName) {
        for (ProductCategory category : this.productCategories) {
            if (category.getCategoryId() == categoryId) {
                category.setCategoryName(categoryName);
                return;
            }
        }
    }
    public void deleteProductCategory(int categoryId) {
        for (ProductCategory category : this.productCategories) {
            if (category.getCategoryId() == categoryId) {
                this.productCategories.remove(category);
                return;
            }
        }
    }



    public void addProduct(int categoryId, Product product) {
        for (ProductCategory category : this.productCategories) {
            if (category.getCategoryId() == categoryId) {
                for (Product p : category.getProducts()) {
                    if (p.getProductCode() == product.getProductCode()) {
                        System.out.println("Product already exists");
                        return;
                    }
                }
                category.addProduct(product);
                return;
            }
        }
    }
    public Product getProduct(int categoryId, int productCode) {
        for (ProductCategory category : this.productCategories) {
            if (category.getCategoryId() == categoryId) {
                for (Product product : category.getProducts()) {
                    if (product.getProductCode() == productCode) {
                        return product;
                    }
                }
            }
        }
        return null;
    }
    public void updateProduct(int categoryId, int productCode, Product product) {
        for (ProductCategory category : this.productCategories) {
            if (category.getCategoryId() == categoryId) {
                for (Product p : category.getProducts()) {
                    if (p.getProductCode() == productCode) {
                        p.setProductName(product.getProductName());
                        p.setPrice(product.getPrice());
                        p.setAmount(product.getAmount());
                        return;
                    }
                }
            }
        }
    }
    public void deleteProduct(int categoryId, int productCode) {
        for (ProductCategory category : this.productCategories) {
            if (category.getCategoryId() == categoryId) {
                for (Product product : category.getProducts()) {
                    if (product.getProductCode() == productCode) {
                        category.deleteProduct(productCode);
                        return;
                    }
                }
            }
        }
    }


    public void showAllProductCategories() {
        for (ProductCategory category : this.productCategories) {
            System.out.println(category);
        }
    }
    public void showAllProducts() {
        for (ProductCategory category : this.productCategories) {
            System.out.println("Category: " + category.getCategoryName());
            for (Product product : category.getProducts()) {
                System.out.println(product);
            }
        }
    }
    public void showAllProductsInCategory(int categoryId) {
        for (Product product : this.productCategories.get(categoryId).getProducts()) {
            System.out.println(product.getProductName());
        }
    }
}
