package com.company.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.internetshop.lib.anotations.Inject;
import com.company.internetshop.model.Role;
import com.company.internetshop.model.User;
import com.company.internetshop.service.UserService;
import com.company.internetshop.exceptions.DataProcessingExeption;
import org.apache.log4j.Logger;

public class RegistrationController extends HttpServlet {
    @Inject
    private static UserService userService;

    private Logger logger = Logger.getLogger(RegistrationController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User newUser = new User();
        newUser.setLogin(req.getParameter("login"));
        newUser.setName(req.getParameter("name"));
        newUser.setSurname(req.getParameter("surname"));
        newUser.setPassword(req.getParameter("password"));
        newUser.addRole(Role.of("USER"));
        try {
            User user = userService.create(newUser);
            HttpSession session = req.getSession(true);
            session.setAttribute("userId", user.getId());
        } catch (DataProcessingExeption dataProcessingExeption) {
            logger.error(dataProcessingExeption);
            req.setAttribute("errorMsg", dataProcessingExeption.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/menu");
    }
}
