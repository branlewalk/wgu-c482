package javacourse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private int partID;
    private int productID;

    void addPart(Part part) {
        allParts.add(part);
    }

    public void addProduct(Product product) {
        allProducts.add(product);
    }

    public Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    public Product lookupProduct(int productId) {
        return allProducts.get(productId);
    }

    public ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> matchedPart = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().equals(partName)) {
                matchedPart.add(part);
            }
        }
        return matchedPart;
    }

    public ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> matchedPart = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().equals(productName)) {
                matchedPart.add(product);
            }
        }
        return matchedPart;
    }

    void updatePart(int index, Part part) {
        allParts.set(index, part);
    }

    public void updateProduct(int index, Product product) {
        allProducts.set(index, product);
    }

    public void deletePart(Part part) {
        allParts.removeAll(part);
    }

    public void deleteProduct(Product product) {
        allProducts.remove(product);
    }

    ObservableList<Part> getAllParts() {
        return allParts;
    }

    ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    int IDGenerator(String type) {
        switch (type) {
            case "Part":
                partID++;
                return partID;
            case "Product":
                productID++;
                return productID;
            default:
                return 1;
        }
    }
}
