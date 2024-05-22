package com.company.internetshop.lib;

import java.util.HashMap;
import java.util.Map;
import com.company.internetshop.factory.DaoAndServiceFactory;
import com.company.internetshop.service.BucketService;
import com.company.internetshop.service.ItemService;
import com.company.internetshop.service.OrderService;
import com.company.internetshop.service.UserService;
import com.company.internetshop.dao.BucketDao;
import com.company.internetshop.dao.ItemDao;
import com.company.internetshop.dao.OrderDao;
import com.company.internetshop.dao.UserDao;

public class AnnotatedClassMap {
    private static final Map<Class, Object> classMap = new HashMap<>();
    
    static  {
        classMap.put(BucketDao.class, DaoAndServiceFactory.getBucketDaoInstance());
        classMap.put(ItemDao.class, DaoAndServiceFactory.getItemDaoInstance());
        classMap.put(OrderDao.class, DaoAndServiceFactory.getOrderDaoInstance());
        classMap.put(UserDao.class, DaoAndServiceFactory.getUserDaoInstance());

        classMap.put(BucketService.class, DaoAndServiceFactory.getBucketServiceInstance());
        classMap.put(ItemService.class, DaoAndServiceFactory.getItemServiceInstance());
        classMap.put(OrderService.class, DaoAndServiceFactory.getOrderServiceInstance());
        classMap.put(UserService.class, DaoAndServiceFactory.getUserServiceInstance());
    }

    public static Object getImplementation(Class interfaceClass) {
        return classMap.get(interfaceClass);
    }
}
