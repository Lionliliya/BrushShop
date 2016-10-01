package com.gmail.liliyayalovchenko.DAO;


import com.gmail.liliyayalovchenko.Domains.Order;
import com.gmail.liliyayalovchenko.Domains.ProductInCart;

import java.util.Date;
import java.util.List;

public interface OrderDAO {

    Order getOrder(int id);

    void saveOrder(int id, Date date, int totalAmount, String delivery, String comments);

    void deleteOrder(int id);

    void saveOrder(Order order);

    List<Order> getOrders();

//    void deleteProduct(ProductInCart product, int id);

    void updateOrderAmount(int id, ProductInCart productInCart, boolean b);

    List<Order> getSortedByDateUp();

    List<Order> getSortedByDateDown();

    List<Order> getSortedByAmountDown();

    List<Order> getAllOrdersByClient(int id);

//    void addProduct(ProductInCart productInCart, int id);
}
