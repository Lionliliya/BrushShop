package com.gmail.liliyayalovchenko.DAO;


import com.gmail.liliyayalovchenko.Domains.ProductInCart;

import java.util.List;

public interface ProductInCartDAO {

    List<ProductInCart> getProductsInCart();

    void saveProductInCart(List<ProductInCart> ProductsCart);

    void saveProductInCart(ProductInCart productInCart);

    ProductInCart getById(int id);

    void delete(ProductInCart product);

    List<ProductInCart> getProductsInCart(int id);
}
