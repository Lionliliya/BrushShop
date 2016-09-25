package com.gmail.liliyayalovchenko.Domains;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Clients")
public class Client implements Serializable{

    @Id
    @GeneratedValue
    private int id;
    @Column(name= "FirstName", nullable = false)
    private String firstName;
    @Column(name="PhoneNumber", nullable = false)
    private String phoneNumber;
    @Column(name="Email", nullable = false)
    private String email;

//    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="client")
//    private List<FeedBack> feedBacks;

    public Client() {}

    public Client(String firstName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
