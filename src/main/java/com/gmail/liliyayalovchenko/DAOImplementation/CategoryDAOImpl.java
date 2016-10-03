package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.CategoryDAO;
import com.gmail.liliyayalovchenko.Domains.Category;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Category getCategoryByName(String  category) {
        Query query = entityManager.createQuery("SELECT a FROM Category a WHERE a.name =:pattern", Category.class);
        query.setParameter("pattern", category);
        return  (Category)query.getResultList().get(0);
    }

    @Override
    public Category getCategoryById(int id) {
        Query query = entityManager.createQuery("SELECT a FROM Category a WHERE a.id =:var", Category.class);
        query.setParameter("var", id);
        return (Category)query.getResultList().get(0);
    }

    @Override
    public void saveCategory(Category category) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(category);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void saveCategory(int id, String name, String info, String metaDescription, String metaKeyWords, String metaTitle) {
        Query query = entityManager.createQuery("SELECT a FROM Category a  WHERE a.id =:var", Category.class);
        query.setParameter("var", id);
        Category resultCategory = (Category) query.getResultList().get(0);
        try{
            entityManager.getTransaction().begin();
            resultCategory.setName(name);
            resultCategory.setInfo(info);
            resultCategory.setMetaDescription(metaDescription);
            resultCategory.setMetaKeyWords(metaKeyWords);
            resultCategory.setMetaTitle(metaTitle);
            entityManager.getTransaction().commit();
        }
        catch(Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Category> getAllCategories() {
        Query query = entityManager.createQuery("SELECT a FROM Category a", Category.class);
        return (List<Category>)query.getResultList();
    }

    @Override
    public void remove(int id) {
        Query query = entityManager.createQuery("SELECT a FROM Category a where a.id = :var", Category.class);
        query.setParameter("var", id);
        Category resultCategory = (Category) query.getResultList().get(0);
        try{
            entityManager.getTransaction().begin();
            entityManager.remove(resultCategory);
            entityManager.getTransaction().commit();
        }
        catch(Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
