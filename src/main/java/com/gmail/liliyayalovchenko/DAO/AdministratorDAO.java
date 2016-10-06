package com.gmail.liliyayalovchenko.DAO;


import com.gmail.liliyayalovchenko.Domains.Administrator;

import java.util.List;

public interface AdministratorDAO {

    String getAdminPassword(String role);

    String getAdminUsername(String role);

    Administrator getAdminByRole(String role);

    Administrator getAdminById(int id);

    List<Administrator> getAllUsers();

    void saveAdmin(Administrator administrator);

    void saveAdmin(int id, String role, String password, String username, String domainName, String emailAddress,
                   String phoneNumber1, String phoneNumber2);
}
