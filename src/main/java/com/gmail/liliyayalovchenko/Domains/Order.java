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
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;
    private int totalAmount;

    public Order() {}

    public Order(Date date, String delivery, String comments, Client client, int totalAmount) {
        this.date = date;
        this.delivery = delivery;
        this.comments = comments;
        this.client = client;
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

    public void setProductsInCart(List<ProductInCart> productsInCart) {
        productsInCart = productsInCart;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void changeTotalAmount(int amount) {
        this.totalAmount -= amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (totalAmount != order.totalAmount) return false;
        if (client != null ? !client.equals(order.client) : order.client != null) return false;
        if (comments != null ? !comments.equals(order.comments) : order.comments != null) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        if (delivery != null ? !delivery.equals(order.delivery) : order.delivery != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (delivery != null ? delivery.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + totalAmount;
        return result;
    }
}
