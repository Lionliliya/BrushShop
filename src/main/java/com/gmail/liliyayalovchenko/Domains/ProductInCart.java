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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductInCart)) return false;

        ProductInCart that = (ProductInCart) o;

        if (price != that.price) return false;
        if (quantity != that.quantity) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        if (product_id != null ? !product_id.equals(that.product_id) : that.product_id != null) return false;
        if (smallimage != null ? !smallimage.equals(that.smallimage) : that.smallimage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = product_id != null ? product_id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (smallimage != null ? smallimage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductInCart{" +
                "product_In_Cart_id=" + product_In_Cart_id +
                ", product_id=" + product_id.getId() +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", order=" + order.getId() +
                ", quantity=" + quantity +
                ", smallimage='" + smallimage + '\'' +
                '}';
    }
}
