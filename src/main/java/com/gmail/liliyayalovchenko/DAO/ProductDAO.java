package com.gmail.liliyayalovchenko.DAO;

import com.gmail.liliyayalovchenko.Domains.Category;
import com.gmail.liliyayalovchenko.Domains.FeedBack;
import com.gmail.liliyayalovchenko.Domains.Product;

import java.util.List;
import java.util.Set;

public interface ProductDAO {

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String CategoryName);

    Product getProductById(int id);

    List<Product> getProductsByCategoryId(int id);

    List<Product> getProductsByBrand(String brand);

    void saveProduct(Product product);

    void saveProduct(int id, String name, int price, String currency, Category productCategory, int amount,
                     String inStock, String description, String shortDesc, String metaDescription,
                     String metaKeyWords, String metaTitle, String image1,
                     String image2, String image3, String image4, boolean isNew, int discount, String brand);

    void addFeedbackToProduct(FeedBack feedBack, int productId);

    List<Product> search(String pattern);

    List<Product> getProductsByCategoryPriceDown(int id);

    List<Product> getProductsByCategoryPriceUp(int id);

    List<Product> getAllPriceDown();

    List<Product> getAllPriceUp();

    void removeFeedBack(FeedBack feedBack);

    void updateProduct(Product productById);

    void removeFromCategory(int id);

    void remove(int id);

    Set<String> getAllBrands();

    List<Product> getProductsByBrandPriceUp(String curBrand);

    List<Product> getProductsByBrandPriceDown(String curBrand);

    List<Product> getProductsByBrandAndCategory(String brandName, int categoryId);

    List<Product> getAllProductsOnSale();
}
