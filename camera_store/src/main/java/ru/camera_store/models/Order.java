package ru.camera_store.models;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Orders", schema = "public")
public class Order {
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(name = "address")
    private String address;
    @Column(name = "status")
    private String status;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "product_in_order",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    List<Product> products;

    @Id
    @SequenceGenerator(name = "order_seq", sequenceName = "order_sequence", allocationSize = 1)
    @GeneratedValue(generator = "order_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    public Order(){}

    public Order(Long id, String status, User user, List<Product> products, String address) {
        this.id = id;
        this.status = status;
        this.products = products;
        this.user = user;
        this.address = address;
    }

    public Order(String status, User user, List<Product> products, String address) {
        this.status = status;
        this.user = user;
        this.products = products;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser(){
       return  this.user;
    }

    public void setUser(User user){
        this.user = user;;
    }

    public List<Product> getProducts(){return this.products;}

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
