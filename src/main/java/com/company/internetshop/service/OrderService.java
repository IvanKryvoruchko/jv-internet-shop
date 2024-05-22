package com.company.internetshop.service;

import java.util.List;

import com.company.internetshop.model.Item;
import com.company.internetshop.model.Order;
import com.company.internetshop.model.User;
import com.company.internetshop.exceptions.DataProcessingExeption;

public interface OrderService extends GenericService<Order, Long> {
    Order completeOrder(List<Item> items, User user) throws DataProcessingExeption;

    List<Order> getUserOrders(User user) throws DataProcessingExeption;
}
