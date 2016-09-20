package com.gmail.liliyayalovchenko.DAO;


import com.gmail.liliyayalovchenko.Domains.ProductInCart;

import java.util.List;

public interface ProductInCartDAO {

    List<ProductInCart> getProductsInCart();

    void saveProductInCart(List<ProductInCart> ProductsCart);

    ProductInCart getById(int id);
}
