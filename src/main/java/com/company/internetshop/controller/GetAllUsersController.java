package com.company.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.internetshop.exceptions.DataProcessingExeption;
import com.company.internetshop.lib.anotations.Inject;
import com.company.internetshop.model.User;
import com.company.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class GetAllUsersController extends HttpServlet {
    @Inject
    private static UserService userService;

    private Logger logger = Logger.getLogger(GetAllUsersController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> userList = null;
        try {
            userList = userService.getAll();
        } catch (DataProcessingExeption dataProcessingExeption) {
            logger.error(dataProcessingExeption);
            req.setAttribute("errorMsg", dataProcessingExeption.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        req.setAttribute("users", userList);
        req.getRequestDispatcher("/WEB-INF/views/allUsers.jsp").forward(req, resp);
    }
}
