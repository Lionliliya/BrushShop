package com.gmail.liliyayalovchenko.DAO;


import com.gmail.liliyayalovchenko.Domains.Category;

import java.util.List;

public interface CategoryDAO {

    Category getCategoryByName(String category);

    Category getCategoryById(int id);

    List<Category> getAllCategories();

    void saveCategory(Category category);

    void saveCategory(int id, String name, String info, String metaDescription, String metaKeyWords,
                      String metaTitle);

    void remove(int id);
}
