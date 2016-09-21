package com.gmail.liliyayalovchenko.DAOImplementation;

import com.gmail.liliyayalovchenko.DAO.ClientDAO;
import com.gmail.liliyayalovchenko.Domains.Client;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Client> getClients() {
        Query query = entityManager.createQuery("SELECT a FROM Client a", Client.class);
        return (List<Client>) query.getResultList();
    }

    @Override
    public Client getClient(int id) {
        Query query = entityManager.createQuery("SELECT a FROM Client a  WHERE a.id =:var", Client.class);
        query.setParameter("var", id);
        return (Client) query.getResultList().get(0);
    }

    @Override
    public Client findClientByPhone(String phone, String email) {
        Query query = entityManager.createQuery("SELECT a FROM Client a WHERE  a.phoneNumber =:var2 and a.email =:var1",
                Client.class);
        query.setParameter("var2", phone);
        query.setParameter("var1", email);
        List<Client> resultList = query.getResultList();
        if (resultList.size() != 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public Client findClientByEmail(String email) {
        Query query = entityManager.createQuery("SELECT a FROM Client a WHERE  a.email =:var1",
                Client.class);
        query.setParameter("var1", email);
        return (Client) query.getResultList().get(0);
    }

    @Override
    public void saveClient(Client client, int id) {
        Query query = entityManager.createQuery("SELECT a FROM Client a  WHERE a.id =:var", Client.class);
        query.setParameter("var", id);
        Client resultClient = (Client) query.getResultList().get(0);
        try{
            entityManager.getTransaction().begin();
            resultClient.setFirstName(client.getFirstName());
            resultClient.setPhoneNumber(client.getPhoneNumber());
            resultClient.setEmail(client.getEmail());
            entityManager.getTransaction().commit();
        }
        catch(Exception ex){
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void addClient(Client client) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.getTransaction().commit();
        }
        catch(Exception ex){
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }

    }
}
