package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.AdministratorDAO;
import com.gmail.liliyayalovchenko.Domains.Administrator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AdministratorDAOImpl implements AdministratorDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public String getAdminPassword(String role) {
        String password;
        Query query = entityManager.createQuery("SELECT a FROM Administrator a WHERE a.role =:var", Administrator.class);
        query.setParameter("var", role);
        Administrator admin = (Administrator)query.getResultList().get(0);
        password = admin.getPassword();
        return password;
    }

    @Override
    public String getAdminUsername (String role) {
        String username;
        Query query = entityManager.createQuery("SELECT a FROM Administrator a WHERE a.role =:var", Administrator.class);
        query.setParameter("var", role);
        Administrator admin = (Administrator)query.getResultList().get(0);
        username = admin.getUsername();
        return username;
    }

    public Administrator getAdminByRole(String role) {
        Query query = entityManager.createQuery("SELECT a FROM Administrator a WHERE a.role =:var", Administrator.class);
        query.setParameter("var", role);
        return (Administrator)query.getResultList().get(0);
    }

    public Administrator getAdminById(int id) {
        Query query = entityManager.createQuery("SELECT a FROM Administrator a WHERE a.id =:var", Administrator.class);
        query.setParameter("var", id);
        return (Administrator)query.getResultList().get(0);
    }

    @Override
    public List<Administrator> getAllUsers() {
        Query query = entityManager.createQuery("SELECT a FROM Administrator a", Administrator.class);
        return (List<Administrator>)query.getResultList();
    }


    @Override
    public void saveAdmin(Administrator administrator) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(administrator);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void saveAdmin(int id, String role, String password, String username, String domainName, String emailAddress,
                          String phoneNumber1, String phoneNumber2) {
        Query query = entityManager.createQuery("SELECT a FROM Administrator a  WHERE a.id =:var", Administrator.class);
        query.setParameter("var", id);
        Administrator resultAdministrator = (Administrator) query.getResultList().get(0);
        try{
            entityManager.getTransaction().begin();
            resultAdministrator.setRole(role);
            resultAdministrator.setPassword(password);
            resultAdministrator.setUsername(username);
            resultAdministrator.setDomainName(domainName);
            resultAdministrator.setEmailAddress(emailAddress);
            resultAdministrator.setPhoneNumber1(phoneNumber1);
            resultAdministrator.setPhoneNumber2(phoneNumber2);
            entityManager.getTransaction().commit();
        }
        catch(Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

}
