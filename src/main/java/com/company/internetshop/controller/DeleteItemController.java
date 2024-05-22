package com.company.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.internetshop.lib.anotations.Inject;
import com.company.internetshop.service.ItemService;
import com.company.internetshop.exceptions.DataProcessingExeption;
import org.apache.log4j.Logger;

public class DeleteItemController extends HttpServlet {
    @Inject
    private static ItemService itemService;

    private Logger logger = Logger.getLogger(DeleteItemController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            itemService.deleteById(Long.valueOf(req.getParameter("item_id")));
        } catch (DataProcessingExeption dataProcessingExeption) {
            logger.error(dataProcessingExeption);
            req.setAttribute("errorMsg", dataProcessingExeption.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/allItems");
    }
}
