package controllers;

import db.DBManager;
import entity.Discipline;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TermCreateController", urlPatterns = "/term-create")
public class TermCreateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       List<Discipline> disciplines = DBManager.getAllActiveDisciplines();
       req.setAttribute("disciplines", disciplines);
       req.getRequestDispatcher("WEB-INF/term-create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String duration = req.getParameter("duration");
        String[] disciplines = req.getParameterValues("disciplines");
        if (duration.equals("")){
            req.setAttribute("message", "1");
            List<Discipline> disciplines1 = DBManager.getAllActiveDisciplines();
            req.setAttribute("disciplines", disciplines1);
            req.getRequestDispatcher("WEB-INF/term-create.jsp").forward(req, resp);
        }else{
            DBManager.createTerm(duration, disciplines);
            resp.sendRedirect("/terms");
        }
    }


}
