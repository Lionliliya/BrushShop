package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.FeedBackDAO;
import com.gmail.liliyayalovchenko.Domains.FeedBack;
import com.gmail.liliyayalovchenko.Domains.Product;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class FeedBackDAOImpl implements FeedBackDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void saveFeedBack(FeedBack feedBack) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(feedBack);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(FeedBack feedBack) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(feedBack);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public List<FeedBack> getAllFeedBacks() {
        Query query = entityManager.createQuery("SELECT a FROM FeedBack a", FeedBack.class);
        return (List<FeedBack>)query.getResultList();
    }

    @Override
    public List<FeedBack> getFeedBacksByClientId(int ClientId) {
        Query query = entityManager.createQuery("SELECT a FROM FeedBack a WHERE a.client.id =:pattern", FeedBack.class);
        query.setParameter("pattern", ClientId);
        return (List<FeedBack>)query.getResultList();
    }

    @Override
    public List<FeedBack> getFeedBacksByProductId(int ProductId) {
        Query query = entityManager.createQuery("SELECT a FROM FeedBack a WHERE a.product.id =:pattern", FeedBack.class);
        query.setParameter("pattern", ProductId);
        return (List<FeedBack>)query.getResultList();
    }

    @Override
    public FeedBack getFeedBackById(int id) {
        Query query = entityManager.createQuery("SELECT a FROM FeedBack a WHERE a.id =:pattern", FeedBack.class);
        query.setParameter("pattern", id);
        return (FeedBack)query.getResultList().get(0);
    }

    @Override
    public void saveFeedBack(int id, Date date, int evaluation, String feedback) {
        Query query = entityManager.createQuery("SELECT a FROM FeedBack a WHERE a.id =:pattern", FeedBack.class);
        query.setParameter("pattern", id);
        FeedBack feedBack = (FeedBack) query.getResultList().get(0);
        try {
            entityManager.getTransaction().begin();
            feedBack.setDate(date);
            feedBack.setEvaluation(evaluation);
            feedBack.setFeedback(feedback);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public List<FeedBack> getAllFeedBacksDateUp() {
        Query query = entityManager.createQuery("SELECT a FROM FeedBack a order by a.date desc", FeedBack.class);
        return query.getResultList();
    }

    @Override
    public List<FeedBack> getAllFeedBacksDateDown() {
        Query query = entityManager.createQuery("SELECT a FROM FeedBack a order by a.date", FeedBack.class);
        return query.getResultList();
    }

    @Override
    public List<FeedBack> getAllFeedBacksRateDown() {
        Query query = entityManager.createQuery("SELECT a FROM FeedBack a order by a.evaluation desc", FeedBack.class);
        return query.getResultList();
    }

    @Override
    public List<FeedBack> getAllFeedBacksRateUp() {
        Query query = entityManager.createQuery("SELECT a FROM FeedBack a order by a.evaluation", FeedBack.class);
        return query.getResultList();
    }

    @Override
    public void remove(FeedBack feedBackById) {
        try {
            entityManager.getTransaction().begin();
            Product product = feedBackById.getProduct();
            product.removeFeedBack(feedBackById);
            entityManager.remove(feedBackById);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }
}
