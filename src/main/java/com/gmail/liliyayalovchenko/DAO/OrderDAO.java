package com.gmail.liliyayalovchenko.DAO;


import com.gmail.liliyayalovchenko.Domains.Order;

import java.util.List;

public interface OrderDAO {

    Order getOrder(int id);

    void saveOrder(int id, String delivery, String comments);

    void deleteOrder(int id);

    void saveOrder(Order order);

    List<Order> getOrders();
}
