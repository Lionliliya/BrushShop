package com.gmail.liliyayalovchenko.Domains;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Info")
public class Post implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String title;

    private String imagePath;
    @Column(nullable = false)
    private String shortDescription;
    @Column(name="datePubl", nullable = false)
    private Date dateOfPublication;
    @Column(nullable = false)
    private String buttonText;
    @Column(nullable = false)
    private String content;
    private String metaTitle;
    @Column(name="KeyWord")
    private String metaKeyWords;
    @Column(name = "MetaDesc")
    private String metaDescription;

    public Post(String title, String imagePath, String shortDescription, Date dateOfPublication,
                String buttonText, String content, String metaDescription, String metaKeyWords, String metaTitle) {
        this.title = title;
        this.imagePath = imagePath;
        this.shortDescription = shortDescription;
        this.dateOfPublication = dateOfPublication;
        this.buttonText = buttonText;
        this.content = content;
        this.metaDescription = metaDescription;
        this.metaKeyWords = metaKeyWords;
        this.metaTitle = metaTitle;
    }

    public Post() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Date getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(Date dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMetaKeyWords() {
        return metaKeyWords;
    }

    public void setMetaKeyWords(String metaKeyWords) {
        this.metaKeyWords = metaKeyWords;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }
}
