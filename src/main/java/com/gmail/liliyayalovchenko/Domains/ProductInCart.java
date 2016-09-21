package com.gmail.liliyayalovchenko.Domains;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Products_In_Cart")
public class ProductInCart implements Serializable{

    @Id
    @GeneratedValue
    private int product_In_Cart_id;

    @OneToOne (fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    private Product product_id;

    @Column(name="Pname", nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private int price;

    private String currency;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private int quantity;

    private String smallimage;


    public ProductInCart() {}

    public ProductInCart(Product product_id, String category, String smallimage, String name,  int price,
                          String currency, int quantity) {
        this.product_id = product_id;
        this.category = category;
        this.smallimage = smallimage;
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.quantity = quantity;
    }

    public int getProduct_In_Cart_id() {
        return product_In_Cart_id;
    }

    public void setProduct_In_Cart_id(int product_In_Cart_id) {
        product_In_Cart_id = product_In_Cart_id;
    }

    public Product getId() {
        return product_id;
    }

    public Product getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Product product_id) {
        this.product_id = product_id;
    }

    public void setId(Product product_id) {
        this.product_id = product_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        category = category;
    }

    public String getSmallimage() {
        return smallimage;
    }

    public void setSmallimage(String smallimage) {
        this.smallimage = smallimage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
