package com.gmail.liliyayalovchenko.Domains;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Products")
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="Pname", nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    private String currency;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "category_id", nullable = false)
    private Category productCategory;

    @Column(nullable = false, length = 120)
    private String brand;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false, length = 120)
    private String inStock;

    @Column(nullable = false)
    private String description;

    private String shortDesc;

    private String image1;

    private String image2;

    private String image3;

    private String image4;

    @Column(name = "isNew")
    private boolean isNew;

    @Column(name = "discount")
    private int discount;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeedBack> feedBackList;

    @Column(name="KeyWord")
    private String metaKeyWords;

    @Column(name = "MetaDesc")
    private String metaDescription;

    private String metaTitle;

    public Product() {}

    public Product(String name, int price, String currency, Category category, int amount, String inStock, String description, String shortDesc, String metaDescription, String metaKeyWords, String metaTitle, String image1, String image2, String image3, String image4, boolean isNew, int discount, String brand) {
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.productCategory = category;
        this.amount = amount;
        this.inStock = inStock;
        this.description = description;
        this.shortDesc = shortDesc;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.isNew = isNew;
        this.discount = discount;
        this.metaKeyWords = metaKeyWords;
        this.metaDescription = metaDescription;
        this.metaTitle = metaTitle;
        this.brand = brand;
    }

    public double getRating() {
        if (feedBackList.size() == 0) {
            return 0;
        } else {
            double mark = 0;
            for (FeedBack a : feedBackList) {
                mark += a.getEvaluation();
            }
            mark /= feedBackList.size();
            return mark;
        }
    }

    public String getStarRate(double mark) {
        if (mark == 5) {
            return "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>";
        } else if (mark > 4 && mark < 5) {
            return "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-half\" aria-hidden=\"true\"></i>";
        } else if (mark == 4) {
            return "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>";
        } else if (mark > 3 && mark < 4) {
            return "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-half\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>";
        } else if (mark == 3) {
            return "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>";

        } else if (mark > 2 && mark < 3) {
            return "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-half\" aria-hidden=\"true\"></i>";
        } else if (mark == 2) {
            return "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>";
        } else {
            return "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>";
        }
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public List<FeedBack> getFeedBackList() {
        return feedBackList;
    }

    public void setFeedBackList(List<FeedBack> feedBackList) {
        this.feedBackList = feedBackList;
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

    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    public void addFeedBack(FeedBack feedBack) {
        this.feedBackList.add(feedBack);
        feedBack.setProduct(this);
    }

    public void removeFeedBack(FeedBack feedBack) {
        this.feedBackList.remove(feedBack);
    }
}
