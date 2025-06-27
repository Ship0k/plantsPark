package by.park.servlet;

import by.park.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

/*
 * Responsible for the authorization of users in the online store
 *
 * @author Ihar Sidarenka
 * @version 0.1 22-Jun-19
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User owner = new User("Owner", "777");
        User forester = new User("Forester", "111");
        HttpSession session = request.getSession();

        String role = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(role, password);

        if (user.equals(owner)) {
            session.setAttribute("User", owner);
            request.getRequestDispatcher("home.html").forward(request, response);
        }else if (user.equals(forester)) {
            session.setAttribute("User", forester);
            request.getRequestDispatcher("home.html").forward(request, response);
        }else {
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.html").forward(request, response);
    }
}
