package controllers;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DisciplineCreateController", urlPatterns = "/discipline-create")
public class DisciplineCreateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/discipline-create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        if (name.isEmpty()){
            req.setAttribute("message", "1");
            req.getRequestDispatcher("WEB-INF/discipline-create.jsp").forward(req, resp);
            return;
        }

        DBManager.createDiscipline(name);
        resp.sendRedirect("/disciplines");
    }
}
