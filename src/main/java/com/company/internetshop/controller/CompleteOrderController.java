package com.company.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.internetshop.exceptions.DataProcessingExeption;
import com.company.internetshop.lib.anotations.Inject;
import com.company.internetshop.model.Bucket;
import com.company.internetshop.model.Item;
import com.company.internetshop.service.BucketService;
import com.company.internetshop.service.OrderService;
import com.company.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class CompleteOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static UserService userService;

    private Logger logger = Logger.getLogger(CompleteOrderController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        try {
            Bucket bucket = bucketService.getByUserId(userId);
            List<Item> items = bucket.getItems();
            orderService.completeOrder(items, userService.get(bucket.getUserId()));
            bucketService.clear(bucket);
        } catch (DataProcessingExeption dataProcessingExeption) {
            logger.error(dataProcessingExeption);
            req.setAttribute("errorMsg", dataProcessingExeption.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/allUserOrders");
    }
}
