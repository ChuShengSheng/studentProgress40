package controllers;

import db.DBManager;
import entity.Discipline;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ModifyDisciplineController", urlPatterns = "/discipline-modify")
public class ModifyDisciplineController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idsToModify");
        Discipline discipline = DBManager.getDisciplineById(id);
        req.setAttribute("discipline", discipline);

        req.getRequestDispatcher("WEB-INF/discipline-modify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String discipline = req.getParameter("discipline");

        if (discipline.isEmpty()){
            Discipline ds = new Discipline(Integer.getInteger(id),discipline, 1);
            req.setAttribute("discipline", discipline);
            req.setAttribute("message", "1");
            req.getRequestDispatcher("WEB-INF/discipline-modify.jsp").forward(req, resp);
            return;
        }

        DBManager.modifyDiscipline(id, discipline);
        resp.sendRedirect("/disciplines");
    }
}
