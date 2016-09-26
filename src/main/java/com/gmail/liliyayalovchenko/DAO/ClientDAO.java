package com.gmail.liliyayalovchenko.DAO;


import com.gmail.liliyayalovchenko.Domains.Client;

import java.util.List;

public interface ClientDAO {

    List<Client> getClients();

    Client getClient(int id);

    Client findClientByPhone(String phone, String email);

    void saveClient(int id, String name, String phone, String email);

    void addClient(Client client);

    Client findClientByEmail(String email);

    List<Client> getSortedByName();

    List<Client> getSortedByEmail();

    void remove(Client client);
}
