package controllers;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteTermsController", urlPatterns = "/term-delete")
public class DeleteTermsController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idsToDelete = req.getParameter("idsToDelete");
        String[] ids = idsToDelete.split(" ");
        for (String id: ids){
            DBManager.deleteTerm(id);
        }
        resp.sendRedirect("/terms");
    }
}
