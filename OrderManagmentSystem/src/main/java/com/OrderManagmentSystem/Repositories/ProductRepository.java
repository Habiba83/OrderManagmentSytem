package com.OrderManagmentSystem.Repositories;
import com.OrderManagmentSystem.Models.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Product getProductBySerialNumber(long serialNumber) {
        for (Product product : products) {
            if (product.getSerialNum() == serialNumber) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product newProduct) {
        for (Product product : products) {
            if (product.getSerialNum() == newProduct.getSerialNum()) {
                throw new IllegalArgumentException("Product with serial number " + newProduct.getSerialNum() + " already exists");
            }
        }
        products.add(newProduct);
    }

    public void updateProduct(Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getSerialNum() == updatedProduct.getSerialNum()) {
                products.set(i, updatedProduct);
                return;
            }
        }
        throw new IllegalArgumentException("Product with serial number " + updatedProduct.getSerialNum() + " not found for update");
    }

    public void deleteProduct(long serialNumber) {
        products.removeIf(product -> product.getSerialNum() == serialNumber);
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> productsInCategory = new ArrayList<>();

        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                productsInCategory.add(product);
            }
        }

        return productsInCategory;
    }
    public List<Map<String, Object>> getRemainingProductsByAllCategory() {
        Map<String, List<Product>> categoryProductMap = new HashMap<>();
        Map<String, Integer> categoryTotalRemainingParts = new HashMap<>();

        for (Product product : products) {
            String category = product.getCategory();
            int remainingParts = product.getCount();

            // Update products list for the category
            categoryProductMap.computeIfAbsent(category, key -> new ArrayList<>()).add(product);

            // Update total remaining parts for the category
            categoryTotalRemainingParts.merge(category, remainingParts, Integer::sum);
        }

        // Create a list of maps representing each category
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, List<Product>> entry : categoryProductMap.entrySet()) {
            String category = entry.getKey();
            List<Product> categoryProducts = entry.getValue();
            int totalRemainingParts = categoryTotalRemainingParts.get(category);
            // Create a map for each category
            Map<String, Object> categoryMap = new HashMap<>();
            categoryMap.put("category", category);
            categoryMap.put("products", categoryProducts);
            categoryMap.put("totalRemainingParts", totalRemainingParts);

            result.add(categoryMap);
        }
        return result;
    }
    public List<Map<String, Object>> getRemainingProductsInSpecifiedCategory(String category) {
        List<Map<String, Object>> result = new ArrayList<>();
        int totalRemainingParts = 0;

        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                // Map for individual product
                Map<String, Object> productMap = new HashMap<>();
                productMap.put("name", product.getName());
                productMap.put("serialNum", product.getSerialNum());
                productMap.put("vendor", product.getVendor());
                productMap.put("category", product.getCategory());
                productMap.put("price", product.getPrice());
                productMap.put("count", product.getCount());

                // Add product map to the result
                result.add(productMap);

                // Update total count
                totalRemainingParts += product.getCount();
            }
        }

        // Map for the total count
        Map<String, Object> totalMap = new HashMap<>();
        totalMap.put("category", category);
        totalMap.put("totalRemainingParts", totalRemainingParts);

        // Add the total map to the result
        result.add(totalMap);

        return result;
    }
}


