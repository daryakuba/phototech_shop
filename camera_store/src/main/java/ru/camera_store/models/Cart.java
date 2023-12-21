package ru.camera_store.models;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Cart", schema = "public")
public class Cart {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "product_in_cart",
            joinColumns = { @JoinColumn(name = "cart_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    List<Product> products;

    @Id
    @SequenceGenerator(name = "cart_seq", sequenceName = "cart_sequence", allocationSize = 1)
    @GeneratedValue(generator = "cart_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    public Cart() {
    }

    public Cart(User user, List<Product> products, long id) {
        this.user = user;
        this.products = products;
        this.id = id;
    }

    public Cart(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    public void setProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    public Long getId(){
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        if (this.products.size() == 0){return null;}
        else {
            return this.products;
        }
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}