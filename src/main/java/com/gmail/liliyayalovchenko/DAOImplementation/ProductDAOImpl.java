package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.ProductDAO;
import com.gmail.liliyayalovchenko.Domains.Category;
import com.gmail.liliyayalovchenko.Domains.FeedBack;
import com.gmail.liliyayalovchenko.Domains.Product;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private EntityManager entityManager;



    @Override
    public List<Product> getAllProducts() {
        Query query = entityManager.createQuery("SELECT a FROM Product a", Product.class);
        return (List<Product>)query.getResultList();
    }

    @Override
    public List<Product> getAllPriceDown() {
        Query query = entityManager.createQuery("SELECT a FROM Product a order by a.price desc ", Product.class);
        return (List<Product>)query.getResultList();
    }

    @Override
    public List<Product> getAllPriceUp() {
        Query query = entityManager.createQuery("SELECT a FROM Product a order by a.price asc ", Product.class);
        return (List<Product>)query.getResultList();
    }

    @Override
    public List<Product> getProductsByCategory(String CategoryName) {
        Query findId = entityManager.createQuery("SELECT c FROM Category c WHERE c.name =:parameter", Category.class);
        findId.setParameter("parameter", CategoryName);
        if (findId.getResultList().size()==0) {
            ArrayList<Product> lipBrushes = new ArrayList<Product>();
            return lipBrushes;
        } else {
            Category categoryWithId = (Category) findId.getResultList().get(0);
            //int id = categoryWithId.getId();
            Query query = entityManager.createQuery("SELECT a FROM Product a WHERE a.productCategory =:var", Product.class);
            query.setParameter("var", categoryWithId);
            return (List<Product>) query.getResultList();
        }
    }

    @Override
    public Product getProductById(int id) {
        Query query = entityManager.createQuery("SELECT a FROM Product a WHERE a.id =:var", Product.class);
        query.setParameter("var", id);
        return (Product)query.getResultList().get(0);
    }

    @Override
    public void saveProduct(Product product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public List<Product> getProductsByCategoryId(int id) {
        Query query = entityManager.createQuery("SELECT a FROM Product a WHERE a.productCategory.id =:var", Product.class);
        query.setParameter("var", id);
        return (List<Product>)query.getResultList();
    }

    @Override
    public List<Product> getProductsByCategoryPriceDown(int id) {
        Query query = entityManager.createQuery("SELECT p FROM Product p WHERE p.productCategory.id =:var order by p.price desc ", Product.class);
        query.setParameter("var", id);
        return (List<Product>)query.getResultList();
    }

    @Override
    public List<Product> getProductsByCategoryPriceUp(int id) {
        Query query = entityManager.createQuery("SELECT p FROM Product p WHERE p.productCategory.id =:var order by p.price asc", Product.class);
        query.setParameter("var", id);
        return (List<Product>)query.getResultList();
    }

    @Override
    public void saveProduct(int id, String name, int price, String currency, Category productCategory, int amount,
                            String inStock, String description, String shortDesc, String metaDescription, String metaKeyWords,
                            String metaTitle, String smallimage, String smallimage1, String image1,
                            String image2, String image3, String image4) {
        Query query = entityManager.createQuery("SELECT a FROM Product a  WHERE a.id =:var", Product.class);
        query.setParameter("var", id);
        Product resultProduct = (Product) query.getResultList().get(0);
        try{
            entityManager.getTransaction().begin();
            resultProduct.setName(name);
            resultProduct.setPrice(price);
            resultProduct.setCurrency(currency);
            resultProduct.setProductCategory(productCategory);
            resultProduct.setAmount(amount);
            resultProduct.setInStock(inStock);
            resultProduct.setDescription(description);
            resultProduct.setShortDesc(shortDesc);
            resultProduct.setMetaDescription(metaDescription);
            resultProduct.setMetaKeyWords(metaKeyWords);
            resultProduct.setMetaTitle(metaTitle);
            resultProduct.setSmallimage(smallimage);
            resultProduct.setSmallimage1(smallimage1);
            resultProduct.setImage1(image1);
            resultProduct.setImage2(image2);
            resultProduct.setImage3(image3);
            resultProduct.setImage4(image4);
            entityManager.getTransaction().commit();
        }
        catch(Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void addFeedbackToProduct(FeedBack feedBack, int productId) {
        Query query = entityManager.createQuery("SELECT p FROM Product p  WHERE p.id =:id", Product.class);
        query.setParameter("id", productId);
        Product product = (Product) query.getResultList().get(0);
        try {
            entityManager.getTransaction().begin();
            product.addFeedBack(feedBack);
            entityManager.refresh(product);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void removeFeedBack(FeedBack feedBack) {
        Query query = entityManager.createQuery("SELECT p FROM Product p  WHERE p.id =:id", Product.class);
        query.setParameter("id", feedBack.getProduct().getId());
        Product product = (Product) query.getResultList().get(0);
        try {

            entityManager.getTransaction().begin();
            product.removeFeedBack(feedBack);
            entityManager.refresh(product);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public List<Product> search(String pattern){
        Query query = entityManager.createQuery("SELECT a FROM Product a WHERE a.name LIKE :pattern", Product.class);
        query.setParameter("pattern", "%" + pattern + "%");
        try {
            List<Product> resultList = (List<Product>) query.getResultList();
            return resultList;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void updateProduct(Product productById) {
        try {

            entityManager.getTransaction().begin();
            entityManager.refresh(productById);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }
}
