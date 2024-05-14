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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TermsControllers",urlPatterns = "/terms")
public class TermsControllers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idSelectedTerm = req.getParameter("idSelectedTerm");
        List<Term> terms = DBManager.getAllActiveTerms();
        req.setAttribute("terms", terms);

        Term selectedTerm = null;
        if (idSelectedTerm != null){   // отработает, если нажали на кнопку ВЫБРАТЬ
            selectedTerm = DBManager.getTermById(idSelectedTerm);
        }else{  // отработает, если нажали на кнопку
            if (terms.size() != 0){
                selectedTerm = terms.get(0);
            }
        }
        req.setAttribute("selectedTerm", selectedTerm);
        ArrayList<Discipline> disciplinesByTerm = (ArrayList<Discipline>) DBManager.getAllActiveDisciplinesByTerm(selectedTerm.getId());
        req.setAttribute("allDisciplineByTerm", disciplinesByTerm);
        req.getRequestDispatcher("WEB-INF/terms.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idsToDelete = req.getParameter("idsToDelete");  // s - имя hidden
        if (idsToDelete != null){
            DBManager.deleteTerm(idsToDelete);
            resp.sendRedirect("/terms");
        }else{
            List<Term> terms = DBManager.getAllActiveTerms();
            String termId = req.getParameter("selectedTerm");
            int selectedId = Integer.parseInt(termId);
            for (Term s: terms ){
                if (s.getId() == selectedId){
                    req.setAttribute("terms", terms);
                    req.setAttribute("selectedTerm",s);
                    List<Discipline> disciplinesByTerm = DBManager.getAllActiveDisciplinesByTerm(selectedId);
                    req.setAttribute("disciplinesByTerm", disciplinesByTerm);
                    req.getRequestDispatcher("WEB-INF/terms.jsp").forward(req,resp);
                }
            }
        }
    }
}
