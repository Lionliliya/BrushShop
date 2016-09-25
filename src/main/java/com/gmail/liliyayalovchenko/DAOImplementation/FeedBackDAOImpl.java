package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.FeedBackDAO;
import com.gmail.liliyayalovchenko.Domains.Client;
import com.gmail.liliyayalovchenko.Domains.FeedBack;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

//    @Override
//    public void delete(int id, FeedBack feedBack) {
//        try {
//            entityManager.getTransaction().begin();
//
//            client.removeFeedBack(feedBack);
//            entityManager.refresh(client);
//            entityManager.getTransaction().commit();
//        } catch (Exception ex) {
//            entityManager.getTransaction().rollback();
//            ex.printStackTrace();
//        }
//    }

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
    public void saveFeedBack(FeedBack feedBack, int id) {
        Query query = entityManager.createQuery("SELECT a FROM FeedBack a  WHERE a.id =:var", Client.class);
        query.setParameter("var", id);
        FeedBack resultFeedBack = (FeedBack) query.getResultList().get(0);
        try{
            entityManager.getTransaction().begin();
            resultFeedBack.setDate(feedBack.getDate());
            resultFeedBack.setEvaluation(feedBack.getEvaluation());
            resultFeedBack.setFeedback(feedBack.getFeedback());
            entityManager.getTransaction().commit();
        }
        catch(Exception ex){
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }
}
