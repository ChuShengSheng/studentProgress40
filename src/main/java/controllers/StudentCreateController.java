package controllers;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet(name = "StudentCreateController", urlPatterns = "/student-create")
public class StudentCreateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/student-create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String groupe = req.getParameter("groupe");
        String date = req.getParameter("date");

        if(surname.isEmpty() || name.isEmpty() || groupe.isEmpty() || date.isEmpty()){
            req.setAttribute("message", "1");
            req.getRequestDispatcher("WEB-INF/student-create.jsp").forward(req, resp);
            return;
        }

        // date (String) --> Date --> String (который на нужен)
        // 25/12/2023 --> Дата --> 2023-12-25

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date dateObj = null;
        try {
            dateObj = format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        DateFormat formatToDB = new SimpleDateFormat("yyyy-MM-dd");
        String dateToDB = formatToDB.format(dateObj);

        DBManager.createStudent(surname, name, groupe, dateToDB);
        resp.sendRedirect("/students");
    }
}
