package controllers;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        if (login.isEmpty() || password.isEmpty() || role.isEmpty()) {
            req.setAttribute("message", "1");
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
            return;
        }

        if (DBManager.canLogin(login, password, role)) {  // если удалось зайти
            req.getSession().setAttribute("isLogin", "1");
            req.getSession().setAttribute("role", role);
            req.getSession().setAttribute("login", login);
            resp.sendRedirect("/");
        } else {  // пользователь ошибся в логине или пароле
            req.setAttribute("message", "2");
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
        }
    }
}
