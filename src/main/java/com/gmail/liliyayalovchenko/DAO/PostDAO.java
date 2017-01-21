package com.gmail.liliyayalovchenko.DAO;


import com.gmail.liliyayalovchenko.Domains.Post;

import java.util.Date;
import java.util.List;

public interface PostDAO {

    List<Post> getAllPosts();

    Post get(int id);

    Post get(String title);

    List<Post> getAllPostAsc();

    List<Post> getAllPostsDesc();

    void save(Post article);

    void remove(int id);

    List<Post> getTwoLatest();

    void save(int id, String title, String imagePath, String shortDescription, Date dateOfPublication,
              String buttonText, String content, String metaDescription, String metaKeyWords, String metaTitle);

    List<Post> getAllPostsNameUp();

    List<Post> getAllPostsNameDown();

    List<Post> getNextPosts(Integer starRow);

}
