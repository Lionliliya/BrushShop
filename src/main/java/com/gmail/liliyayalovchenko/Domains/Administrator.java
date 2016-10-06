package com.gmail.liliyayalovchenko.Domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="Admins")
public class Administrator implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String role;
    private String password;
    private String username;
    private String domainName;
    private String emailAddress;
    private String phoneNumber1;
    private String phoneNumber2;

    public Administrator(String role, String password, String username, String domainName, String emailAddress,
                         String phoneNumber1, String phoneNumber2) {

        this.role = role;
        this.password = password;
        this.username = username;
        this.domainName = domainName;
        this.emailAddress = emailAddress;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
    }

    public Administrator() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Administrator)) return false;

        Administrator that = (Administrator) o;

        if (emailAddress != null ? !emailAddress.equals(that.emailAddress) : that.emailAddress != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (phoneNumber1 != null ? !phoneNumber1.equals(that.phoneNumber1) : that.phoneNumber1 != null) return false;
        if (phoneNumber2 != null ? !phoneNumber2.equals(that.phoneNumber2) : that.phoneNumber2 != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (phoneNumber1 != null ? phoneNumber1.hashCode() : 0);
        result = 31 * result + (phoneNumber2 != null ? phoneNumber2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", domainName='" + domainName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", phoneNumber1='" + phoneNumber1 + '\'' +
                ", phoneNumber2='" + phoneNumber2 + '\'' +
                '}';
    }
}
