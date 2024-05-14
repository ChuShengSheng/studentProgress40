package controllers;

import db.DBManager;
import entity.Discipline;
import entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ModifyTermController", urlPatterns = "/term-modify")
public class ModifyTermController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idsToModify");
        Term selectedTerm = DBManager.getTermById(id);
        List<Discipline> disciplines = DBManager.getAllActiveDisciplines();
        req.setAttribute("selectedTerm", selectedTerm);
        req.setAttribute("disciplines", disciplines);
        req.getRequestDispatcher("WEB-INF/term-modify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String modifiedDuration = req.getParameter("modifiedDuration");
        String[] modifiedDisciplineId = req.getParameterValues("modifiedDisciplineId");

        if (modifiedDuration.equals("")) {
            req.setAttribute("message", "1");
            List<Discipline> disciplines1 = DBManager.getAllActiveDisciplines();
            req.setAttribute("disciplines", disciplines1);
            req.getRequestDispatcher("WEB-INF/term-modify.jsp").forward(req, resp);
        }else {
            DBManager.modifyTerm(id, modifiedDuration, modifiedDisciplineId);
            resp.sendRedirect("/terms");
        }
    }
}
