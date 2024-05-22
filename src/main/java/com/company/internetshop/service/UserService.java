package com.company.internetshop.service;

import com.company.internetshop.exceptions.AuthenticationException;
import com.company.internetshop.exceptions.DataProcessingExeption;
import com.company.internetshop.model.User;

public interface UserService extends GenericService<User, Long> {

    User login(String login, String password)
            throws AuthenticationException, DataProcessingExeption;
}
