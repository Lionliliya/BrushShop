package com.gmail.liliyayalovchenko.DAO;

import com.gmail.liliyayalovchenko.Domains.Category;
import com.gmail.liliyayalovchenko.Domains.FeedBack;
import com.gmail.liliyayalovchenko.Domains.Product;

import java.util.List;

public interface ProductDAO {

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String CategoryName);

    Product getProductById(int id);

    List<Product> getProductsByCategoryId(int id);

    void saveProduct(Product product);

    void saveProduct(int id, String name, int price, String currency, Category productCategory, int amount,
                     String inStock, String description, String shortDesc, String metaDescription,
                     String metaKeyWords, String metaTitle, String smallimage, String smallimage1, String image1,
                     String image2, String image3, String image4);

    void addFeedbackToProduct(FeedBack feedBack, int productId);

    List<Product> search(String pattern);
}
