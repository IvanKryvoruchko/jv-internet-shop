package com.company.internetshop.dao;

import java.util.Optional;

import com.company.internetshop.model.User;
import com.company.internetshop.exceptions.DataProcessingExeption;

public interface UserDao extends GenericDao<User, Long> {
    Optional<User> findByLogin(String login) throws DataProcessingExeption;
}
