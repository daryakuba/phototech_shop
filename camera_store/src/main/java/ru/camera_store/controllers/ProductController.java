package ru.camera_store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.camera_store.models.Category;
import ru.camera_store.models.Product;
import ru.camera_store.services.ProductService;

import java.util.List;

class ProductsByCategory {
    public String Category;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/products/updStatus/{product_id}/{newStatus}")
    public void updStatus(@PathVariable long product_id, @PathVariable String newStatus){
        service.updateProductStatus(product_id, newStatus);
    }

    @PostMapping("/products")
    public void product(@RequestBody Product product){
        service.addProduct(product);
    }

    @GetMapping("/products")
    public @ResponseBody List<Product> getAll(){
        return service.getProducts();
    }

    @GetMapping("/products/{id}")
    public @ResponseBody List<Product> getProducts(@PathVariable Long id){
        return service.getProductsByCategory(id);
    }

    @GetMapping(value = "/product/{productId}/category")
    public @ResponseBody Category getProductCategory(@PathVariable("productId") Long productId){
        return service.getCategoryByProduct(productId);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable long id){
        service.deleteProduct(id);
    }
}
