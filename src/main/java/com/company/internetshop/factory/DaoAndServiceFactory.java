package com.company.internetshop.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.company.internetshop.dao.BucketDao;
import com.company.internetshop.dao.ItemDao;
import com.company.internetshop.dao.OrderDao;
import com.company.internetshop.dao.UserDao;
import com.company.internetshop.dao.jdbc.BucketDaoJdbcImpl;
import com.company.internetshop.dao.jdbc.ItemDaoJdbcImpl;
import com.company.internetshop.dao.jdbc.OrderDaoJdbcImpl;
import com.company.internetshop.dao.jdbc.UserDaoJdbcImpl;
import com.company.internetshop.service.BucketService;
import com.company.internetshop.service.ItemService;
import com.company.internetshop.service.OrderService;
import com.company.internetshop.service.UserService;
import com.company.internetshop.service.impl.BucketServiceImpl;
import com.company.internetshop.service.impl.ItemServiceImpl;
import com.company.internetshop.service.impl.OrderServiceImpl;
import com.company.internetshop.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

public class DaoAndServiceFactory {
    private static BucketDao bucketDaoInstance;
    private static ItemDao itemDaoInstance;
    private static OrderDao orderDaoInstance;
    private static UserDao userDaoInstance;

    private static BucketService bucketServiceInstance;
    private static ItemService itemServiceInstance;
    private static OrderService orderServiceInstance;
    private static UserService userServiceInstance;

    private static Connection connection;
    private static final Logger LOGGER = Logger.getLogger(DaoAndServiceFactory.class);

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internetShop_db?"
                    + "user=ivan&password=1234&serverTimezone=UTC");
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.warn("Can't establish connection to our DB", e);
        }
    }

    public static BucketDao getBucketDaoInstance() {
        if (bucketDaoInstance == null) {
            bucketDaoInstance = new BucketDaoJdbcImpl(connection);
        }
        return bucketDaoInstance;
    }

    public static ItemDao getItemDaoInstance() {
        if (itemDaoInstance == null) {
            itemDaoInstance = new ItemDaoJdbcImpl(connection);
        }
        return itemDaoInstance;
    }

    public static OrderDao getOrderDaoInstance() {
        if (orderDaoInstance == null) {
            orderDaoInstance = new OrderDaoJdbcImpl(connection);
        }
        return orderDaoInstance;
    }

    public static UserDao getUserDaoInstance() {
        if (userDaoInstance == null) {
            userDaoInstance = new UserDaoJdbcImpl(connection);
        }
        return userDaoInstance;
    }

    public static BucketService getBucketServiceInstance() {
        if (bucketServiceInstance == null) {
            bucketServiceInstance = new BucketServiceImpl();
        }
        return bucketServiceInstance;
    }

    public static ItemService getItemServiceInstance() {
        if (itemServiceInstance == null) {
            itemServiceInstance = new ItemServiceImpl();
        }
        return itemServiceInstance;
    }

    public static OrderService getOrderServiceInstance() {
        if (orderServiceInstance == null) {
            orderServiceInstance = new OrderServiceImpl();
        }
        return orderServiceInstance;
    }

    public static UserService getUserServiceInstance() {
        if (userServiceInstance == null) {
            userServiceInstance = new UserServiceImpl();
        }
        return userServiceInstance;
    }
}
