package com.company.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.internetshop.lib.anotations.Inject;
import com.company.internetshop.model.Bucket;
import com.company.internetshop.service.BucketService;
import com.company.internetshop.exceptions.DataProcessingExeption;
import org.apache.log4j.Logger;

public class BucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    private Logger logger = Logger.getLogger(BucketController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Bucket bucket = null;
        try {
            bucket = bucketService.getByUserId(userId);
        } catch (DataProcessingExeption dataProcessingExeption) {
            logger.error(dataProcessingExeption);
            req.setAttribute("errorMsg", dataProcessingExeption.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        req.setAttribute("bucket", bucket);
        req.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}

