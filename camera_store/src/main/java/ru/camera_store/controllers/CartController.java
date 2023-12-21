package ru.camera_store.controllers;

import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.camera_store.models.Cart;
import ru.camera_store.models.Product;
import ru.camera_store.services.CartService;

class voidProducts {
    List<Product> products;

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    public void setCart(@RequestBody Cart cart){
        cartService.addCart(cart);
    }

    @GetMapping("/cart/updProducts/{cart_id}")
    public void updProducts(@PathVariable long cart_id){
        cartService.updateProducts(cart_id);
    }

    @GetMapping("/cart/addProduct/{cart_id}/{product_id}")
    public void addProduct(@PathVariable long cart_id, @PathVariable long product_id){
        cartService.addProduct(cart_id, product_id);
    }

    @GetMapping("/cart/removeProduct/{cart_id}/{product_id}")
    public void removeProduct(@PathVariable long cart_id, @PathVariable long product_id){
        cartService.removeProduct(cart_id, product_id);
    }

    @GetMapping("/cart/getByUser/{user_id}")
    public Long getCartByUser(@PathVariable long user_id){
        return cartService.getCartByUser(user_id);
    }

    @GetMapping("/cart/isCartExist/{user_id}")
    public String isCartExist(@PathVariable long user_id){
        try {
            if (cartService.getCartByUser(user_id) != null) {
                return "OK";
            }
        }
        catch (NoResultException e){
            return "ERROR";
        }
        return "ERROR";
    }

    @DeleteMapping("/cart/clear/{id}")
    public void deleteCart(@PathVariable long id){
        cartService.deleteCart(id);
    }

    @GetMapping("/cart/getProducts/{cart_id}")
    public List<Product> getProductsFromCart(@PathVariable Long cart_id){
        return cartService.getCart(cart_id).getProducts();
    }
}
