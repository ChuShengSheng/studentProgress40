package controllers;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteDisciplinesController", urlPatterns = "/discipline-delete")
public class DeleteDisciplinesController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 3 5 7
        String idsToDelete = req.getParameter("idsToDelete");
        String[] ids = idsToDelete.split(" ");
        for (String id: ids){
            DBManager.deleteDiscipline(id);
        }
        resp.sendRedirect("/disciplines");
    }
}
