package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.PostDAO;
import com.gmail.liliyayalovchenko.Domains.Post;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class PostDAOImpl implements PostDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Post> getAllPosts() {
        Query query = entityManager.createQuery("SELECT a FROM Post a", Post.class);
        return (List<Post>)query.getResultList();
    }

    @Override
    public Post get(int id) {
        Query query = entityManager.createQuery("SELECT a FROM Post a WHERE a.id =:var", Post.class);
        query.setParameter("var", id);
        return (Post)query.getResultList().get(0);
    }

    @Override
    public Post get(String title) {
        Query query = entityManager.createQuery("SELECT a FROM Post a WHERE a.id =:var", Post.class);
        query.setParameter("var", title);
        return (Post)query.getResultList().get(0);
    }

    @Override
    public List<Post> getAllPostAsc() {
        Query query = entityManager.createQuery("SELECT a FROM Post a ORDER BY a.dateOfPublication",
                Post.class);
        return (List<Post>)query.getResultList();
    }

    @Override
    public List<Post> getAllPostsDesc() {
        Query query = entityManager.createQuery("SELECT a FROM Post a ORDER BY a.dateOfPublication DESC ",
                Post.class);
        return (List<Post>)query.getResultList();
    }

    @Override
    public List<Post> getAllPostsNameUp() {
        Query query = entityManager.createQuery("SELECT a FROM Post a ORDER BY a.title",
                Post.class);
        return (List<Post>)query.getResultList();
    }

    @Override
    public List<Post> getAllPostsNameDown() {
        Query query = entityManager.createQuery("SELECT a FROM Post a ORDER BY a.title DESC ",
                Post.class);
        return (List<Post>)query.getResultList();
    }

    @Override
    public List<Post> getNextPosts(Integer startRow) {
        Query query = entityManager.createQuery("SELECT a FROM Post a order by a.dateOfPublication desc",
                Post.class);
        if (startRow == 1) {
            return query.setFirstResult(0).setMaxResults(3).getResultList();
        }
        return query.setFirstResult(startRow - 1).setMaxResults(3).getResultList();
    }

    @Override
    public void save(Post article) {
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
    public void remove(int id) {
        Query query = entityManager.createQuery("SELECT a FROM Post a  WHERE a.id =:var", Post.class);
        query.setParameter("var", id);
        Post infoToDelete = (Post) query.getResultList().get(0);
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
    public void save(int id, String title, String imagePath, String shortDescription, Date dateOfPublication,
                     String buttonText, String content, String metaDescription, String metaKeyWords, String metaTitle) {

        Query query = entityManager.createQuery("SELECT a FROM Post a  WHERE a.id =:var", Post.class);
        query.setParameter("var", id);
        Post infoToSave = (Post) query.getResultList().get(0);
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
    public List<Post> getTwoLatest() {
        Query query = entityManager.createQuery("SELECT i from Post i order by id DESC");
        query.setMaxResults(2);
        return query.getResultList();
    }
}
