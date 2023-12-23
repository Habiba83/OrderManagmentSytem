package com.OrderManagmentSystem.Controllers;

import com.OrderManagmentSystem.Models.Product;
import com.OrderManagmentSystem.Services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{serialNumber}")
    public Product getProductBySerialNumber(@PathVariable long serialNumber) {
        return productService.getProductBySerialNumber(serialNumber);
    }

    @PostMapping
    public void addProduct(@RequestBody Product newProduct) {
        productService.addProduct(newProduct);
    }

    @PutMapping("/{serialNumber}")
    public void updateProduct(@PathVariable long serialNumber, @RequestBody Product updatedProduct) {
        updatedProduct.setSerialNum(serialNumber);
        productService.updateProduct(updatedProduct);
    }

    @DeleteMapping("/{serialNumber}")
    public void deleteProduct(@PathVariable long serialNumber) {
        productService.deleteProduct(serialNumber);
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/remaining")
    public List<Map<String, Object>> getRemainingProductsByAllCategory() {
        return productService.getRemainingProductsByAllCategory();
    }

    @GetMapping("/remaining/{category}")
    public List<Map<String, Object>> getRemainingProductsInSpecifiedCategory(@PathVariable String category) {
        return productService.getRemainingProductsInSpecifiedCategory(category);
    }
}
