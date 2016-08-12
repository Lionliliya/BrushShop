package com.gmail.liliyayalovchenko.DAO;


import com.gmail.liliyayalovchenko.Domains.Information;

import java.util.Date;
import java.util.List;

public interface InformationDAO {

    List<Information> getAllArticles();

    Information getAllArticles(int id);

    Information getAllArticleByName(String title);

    List<Information> getAllArticlesOrdered();

    List<Information> getAllArticlesDesc();

    void addArticle(Information article);

    void deleteArticle(int id);

    List<Information> getTwoLatest();

    void changeArticle(int id, String title, String imagePath, String shortDescription, Date dateOfPublication,
                       String buttonText, String content, String metaDescription, String metaKeyWords, String metaTitle);
}
