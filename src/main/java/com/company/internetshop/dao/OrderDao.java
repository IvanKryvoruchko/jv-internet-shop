package com.company.internetshop.dao;

import java.util.List;

import com.company.internetshop.model.Order;
import com.company.internetshop.model.User;
import com.company.internetshop.exceptions.DataProcessingExeption;

public interface OrderDao extends GenericDao<Order, Long> {
    List<Order> getUserOrders(User user) throws DataProcessingExeption;
}
