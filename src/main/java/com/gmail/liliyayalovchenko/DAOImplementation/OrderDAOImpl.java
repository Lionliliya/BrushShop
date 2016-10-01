package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.OrderDAO;
import com.gmail.liliyayalovchenko.Domains.Order;
import com.gmail.liliyayalovchenko.Domains.ProductInCart;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void deleteOrder(int id) {
        Query query = entityManager.createQuery("SELECT a FROM Order a  WHERE a.id =:var", Order.class);
        query.setParameter("var", id);
        Order resultOrder = (Order) query.getResultList().get(0);
        try{
            entityManager.getTransaction().begin();
            entityManager.remove(resultOrder);
            entityManager.getTransaction().commit();
        }
        catch(Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    @Override
    public Order getOrder(int id) {
        Query query = entityManager.createQuery("SELECT a FROM Order a  WHERE a.id =:var", Order.class);
        query.setParameter("var", id);
        return (Order) query.getResultList().get(0);
    }

    @Override
    public void updateOrderAmount(int id, ProductInCart productInCart, boolean b) {
        Query query = entityManager.createQuery("SELECT a FROM Order a  WHERE a.id =:var", Order.class);
        query.setParameter("var", id);
        Order resultOrder = (Order) query.getResultList().get(0);
        int newAmount = b ? resultOrder.getTotalAmount() + productInCart.getQuantity()*productInCart.getPrice()
                          : resultOrder.getTotalAmount() - productInCart.getQuantity()*productInCart.getPrice();
        try{
            entityManager.getTransaction().begin();
            resultOrder.setTotalAmount(newAmount);
            entityManager.getTransaction().commit();
        }
        catch(Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void saveOrder(int id, Date date, int totalAmount,  String delivery, String comments) {
        Query query = entityManager.createQuery("SELECT a FROM Order a  WHERE a.id =:var", Order.class);
        query.setParameter("var", id);
        Order resultOrder = (Order) query.getResultList().get(0);
        try{
            entityManager.getTransaction().begin();
            resultOrder.setDate(date);
            resultOrder.setTotalAmount(totalAmount);
            resultOrder.setDelivery(delivery);
            resultOrder.setComments(comments);
            entityManager.getTransaction().commit();
        }
        catch(Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void saveOrder(Order order) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(order);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public List<Order> getOrders() {
        Query query = entityManager.createQuery("SELECT a FROM Order a", Order.class);
        return (List<Order>)query.getResultList();
    }

    @Override
    public List<Order> getAllOrdersByClient(int id) {
        Query query = entityManager.createQuery("SELECT o FROM Order o where o.client.id =:var", Order.class);
        query.setParameter("var", id);
        return (List<Order>)query.getResultList();
    }

    @Override
    public List<Order> getSortedByDateUp() {
        Query query = entityManager.createQuery("SELECT o FROM Order o order by o.date desc ", Order.class);
        return (List<Order>)query.getResultList();
    }

    @Override
    public List<Order> getSortedByDateDown() {
        Query query = entityManager.createQuery("SELECT o FROM Order o order by o.date asc", Order.class);
        return (List<Order>)query.getResultList();
    }

    @Override
    public List<Order> getSortedByAmountDown() {
        Query query = entityManager.createQuery("SELECT o FROM Order o order by o.totalAmount desc", Order.class);
        return (List<Order>)query.getResultList();
    }
}
