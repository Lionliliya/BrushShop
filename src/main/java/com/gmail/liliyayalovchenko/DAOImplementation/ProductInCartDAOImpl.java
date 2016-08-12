package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.ProductInCartDAO;
import com.gmail.liliyayalovchenko.Domains.ProductInCart;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ProductInCartDAOImpl implements ProductInCartDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ProductInCart> getProductsInCart() {
        Query query = entityManager.createQuery("SELECT a FROM ProductInCart a", ProductInCart.class);
        return (List<ProductInCart>)query.getResultList();
    }

    @Override
    public void saveProductInCart(List<ProductInCart> productInCartList) {
        try {
            entityManager.getTransaction().begin();
            productInCartList.forEach(entityManager::persist);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }
}
