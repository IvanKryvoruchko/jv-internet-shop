package com.company.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.internetshop.lib.anotations.Inject;
import com.company.internetshop.model.Role;
import com.company.internetshop.model.User;
import com.company.internetshop.service.UserService;
import com.company.internetshop.exceptions.DataProcessingExeption;
import org.apache.log4j.Logger;

public class InjectDefaultUsersController extends HttpServlet {
    @Inject
    private static UserService userService;

    private Logger logger = Logger.getLogger(RegistrationController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User newUser = new User();
        newUser.setLogin("User1");
        newUser.setName("User");
        newUser.setSurname("User");
        newUser.setPassword("1");
        newUser.addRole(Role.of("USER"));

        User admin = new User();
        admin.setLogin("Admin1");
        admin.setName("Admin");
        admin.setSurname("Admin");
        admin.setPassword("1");
        admin.addRole(Role.of("ADMIN"));
        try {
            userService.create(newUser);
            userService.create(admin);
        } catch (DataProcessingExeption dataProcessingExeption) {
            logger.error(dataProcessingExeption);
            req.setAttribute("errorMsg", dataProcessingExeption.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/menu");
    }
}
