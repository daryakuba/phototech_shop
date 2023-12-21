package ru.camera_store.models;

import javax.persistence.*;

@Entity
@Table(name = "Products", schema = "public")
public class Product {
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private String status;
    @Column(name = "price")
    private int price;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;

    @Id
    @SequenceGenerator(name = "products_seq", sequenceName = "products_sequence", allocationSize = 1)
    @GeneratedValue(generator = "products_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    public Product() {
    }

    public Product(String name, String status, int price, String description, Category category, long id) {
        this.name = name;
        this.status = status;
        this.price = price;
        this.description = description;
        this.category = category;
        this.id = id;
    }

    public Product(String name, String status, String description, int price, Category category) {
        this.name = name;
        this.status = status;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public String getName() {return name;}

    public String getStatus() {return status;}

    public Category getCategory(){
        return this.category;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    public int getPrice() {return price;}

    public String getDescription() {
        return description;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
