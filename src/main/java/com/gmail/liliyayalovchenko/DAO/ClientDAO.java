package com.gmail.liliyayalovchenko.DAO;


import com.gmail.liliyayalovchenko.Domains.Client;

import java.util.List;

public interface ClientDAO {

    List<Client> getClients();

    Client getClient(int id);

    Client findClientByNameAndEmail(String FirstName, String Email);

    void saveClient(Client client, int id);

    void addClient(Client client);
}
