package com.company.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.internetshop.exceptions.DataProcessingExeption;
import com.company.internetshop.lib.anotations.Inject;
import com.company.internetshop.model.Role;
import com.company.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class DeleteUserController extends HttpServlet {
    @Inject
    private static UserService userService;

    private Logger logger = Logger.getLogger(DeleteUserController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Long userId = Long.valueOf(req.getParameter("user_id"));
            userService.deleteById(userId);
        } catch (DataProcessingExeption dataProcessingExeption) {
            logger.error(dataProcessingExeption);
            req.setAttribute("errorMsg", dataProcessingExeption.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/allUsers");
    }

    private boolean checkIfAdmin(Long userId) throws DataProcessingExeption {
        return userService.get(userId).getRoles()
                .stream()
                .anyMatch(r -> r.getRoleName().equals(Role.RoleName.ADMIN));
    }
}
