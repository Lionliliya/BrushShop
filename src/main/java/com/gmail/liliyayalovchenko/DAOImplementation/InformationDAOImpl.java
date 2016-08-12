package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.InformationDAO;
import com.gmail.liliyayalovchenko.Domains.Information;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class InformationDAOImpl implements InformationDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Information> getAllArticles() {
        Query query = entityManager.createQuery("SELECT a FROM Information a", Information.class);
        return (List<Information>)query.getResultList();
    }

    @Override
    public Information getAllArticles(int id) {
        Query query = entityManager.createQuery("SELECT a FROM Information a WHERE a.id =:var", Information.class);
        query.setParameter("var", id);
        return (Information)query.getResultList().get(0);
    }

    @Override
    public Information getAllArticleByName(String title) {
        Query query = entityManager.createQuery("SELECT a FROM Information a WHERE a.id =:var", Information.class);
        query.setParameter("var", title);
        return (Information)query.getResultList().get(0);
    }

    @Override
    public List<Information> getAllArticlesOrdered() {
        Query query = entityManager.createQuery("SELECT a FROM Information a ORDER BY a.dateOfPublication",
                Information.class);
        return (List<Information>)query.getResultList();
    }

    @Override
    public List<Information> getAllArticlesDesc() {
        Query query = entityManager.createQuery("SELECT a FROM Information a ORDER BY a.dateOfPublication DESC ",
                Information.class);
        return (List<Information>)query.getResultList();
    }

    @Override
    public void addArticle(Information article) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(article);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteArticle(int id) {
        Query query = entityManager.createQuery("SELECT a FROM Information a  WHERE a.id =:var", Information.class);
        query.setParameter("var", id);
        Information infoToDelete = (Information) query.getResultList().get(0);
        try{
            entityManager.getTransaction().begin();
            entityManager.remove(infoToDelete);
            entityManager.getTransaction().commit();
        }
        catch(Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void changeArticle(int id, String title, String imagePath, String shortDescription, Date dateOfPublication,
                              String buttonText, String content, String metaDescription, String metaKeyWords, String metaTitle) {

        Query query = entityManager.createQuery("SELECT a FROM Information a  WHERE a.id =:var", Information.class);
        query.setParameter("var", id);
        Information infoToSave = (Information) query.getResultList().get(0);
        try{
            entityManager.getTransaction().begin();
            infoToSave.setTitle(title);
            infoToSave.setImagePath(imagePath);
            infoToSave.setShortDescription(shortDescription);
            infoToSave.setDateOfPublication(dateOfPublication);
            infoToSave.setButtonText(buttonText);
            infoToSave.setContent(content);
            infoToSave.setMetaDescription(metaDescription);
            infoToSave.setMetaKeyWords(metaKeyWords);
            infoToSave.setMetaTitle(metaTitle);
            entityManager.getTransaction().commit();
        }
        catch(Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }


    }

    @Override
    public List<Information> getTwoLatest() {
        Query query = entityManager.createQuery("SELECT i from Information i order by id DESC");
        query.setMaxResults(2);
        return query.getResultList();
    }
}
