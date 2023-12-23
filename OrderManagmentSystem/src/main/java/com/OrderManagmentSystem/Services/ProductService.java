package com.OrderManagmentSystem.Services;
import com.OrderManagmentSystem.Models.Product;
import com.OrderManagmentSystem.Repositories.ProductRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Product getProductBySerialNumber(long serialNumber) {
        return productRepository.getProductBySerialNumber(serialNumber);
    }

    public void addProduct(Product newProduct) {
        productRepository.addProduct(newProduct);
    }

    public void updateProduct(Product updatedProduct) {
        productRepository.updateProduct(updatedProduct);
    }

    public void deleteProduct(long serialNumber) {
        productRepository.deleteProduct(serialNumber);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.getProductsByCategory(category);
    }

    public List<Map<String, Object>> getRemainingProductsByAllCategory() {
        return productRepository.getRemainingProductsByAllCategory();
    }

    public List<Map<String, Object>> getRemainingProductsInSpecifiedCategory(String category) {
        return productRepository.getRemainingProductsInSpecifiedCategory(category);
    }
}
