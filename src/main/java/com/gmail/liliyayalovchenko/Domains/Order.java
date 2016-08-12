package com.gmail.liliyayalovchenko.Domains;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private String delivery;
    private String comments;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductInCart> productsInCart;
    private int totalAmount = calculateTotalAmount(productsInCart);

    public Order() {}

    public Order(Date date, String delivery, String comments, Client client, List<ProductInCart> productsInCart, int totalAmount) {
        this.date = date;
        this.delivery = delivery;
        this.comments = comments;
        this.client = client;
        this.productsInCart = productsInCart;
        this.totalAmount = totalAmount;
    }

    public int calculateTotalAmount(List<ProductInCart> productsInCart) {
        if (productsInCart == null) {
            return 0;
        } else {
            int totalAmount = 0;
            for (ProductInCart aProductsInCart : productsInCart) {
                totalAmount += aProductsInCart.getPrice();
            }
            return totalAmount;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ProductInCart> getProductsInCart() {
        return productsInCart;
    }

    public void setProductsInCart(List<ProductInCart> productsInCart) {
        productsInCart = productsInCart;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
