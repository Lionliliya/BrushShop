package com.gmail.liliyayalovchenko.Domains;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Categories")
public class Category implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Column(name="Cname", nullable = false)
    private String name;
    private String info;
    @Column(name="KeyWord")
    private String metaKeyWords;
    @Column(name = "MetaDesc")
    private String metaDescription;
    private String metaTitle;

    public Category() {}

    public Category(String name, String info, String metaDescription, String metaKeyWords, String metaTitle) {
        this.name = name;
        this.info = info;
        this.metaDescription = metaDescription;
        this.metaKeyWords = metaKeyWords;
        this.metaTitle = metaTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (!info.equals(category.info)) return false;
        if (!metaDescription.equals(category.metaDescription)) return false;
        if (!metaKeyWords.equals(category.metaKeyWords)) return false;
        if (!metaTitle.equals(category.metaTitle)) return false;
        if (!name.equals(category.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + info.hashCode();
        result = 31 * result + metaKeyWords.hashCode();
        result = 31 * result + metaDescription.hashCode();
        result = 31 * result + metaTitle.hashCode();
        return result;
    }
}
