package com.gmail.liliyayalovchenko.Domains;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Feedbacks")
public class FeedBack implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "product_id")
    private Product product;
    private Date date;
    @ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "client_id")
    private Client client;
    private int evaluation;
    private String feedback;


    public FeedBack() {}

    public FeedBack(Product product, Date date, Client client, int evaluation, String feedback) {
        this.product = product;
        this.date = date;
        this.client = client;
        this.evaluation = evaluation;
        this.feedback = feedback;
    }

    public String getStarRate() {
        if (this.evaluation == 5) {
            return "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>";
        } else if (this.evaluation == 4) {
            return "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>";
        } else if (this.evaluation == 3) {
            return "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>";
        } else if (this.evaluation == 2) {
            return "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>" +
                    "<i class=\"fa fa-star-empty\" aria-hidden=\"true\"></i>";
        } else {
            return "<i class=\"fa fa-star\" aria-hidden=\"true\"></i>" +
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

}
