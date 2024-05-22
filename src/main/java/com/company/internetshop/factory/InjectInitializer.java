package com.company.internetshop.factory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.company.internetshop.lib.Injector;
import org.apache.log4j.Logger;

public class InjectInitializer implements ServletContextListener {
    private final Logger logger = Logger.getLogger(InjectInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            logger.info("Start injectionDependency");
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            logger.error("Can't initialized all dependencies ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
