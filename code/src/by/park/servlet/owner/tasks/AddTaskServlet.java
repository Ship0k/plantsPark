package by.park.servlet.owner.tasks;

import by.park.dao.OwnerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet("/owner/addTask")
public class AddTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add-task.html").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String task = "";
        String plant = "";
        if (br != null) {
            String str = br.readLine();
            task += String.valueOf(str.charAt(0));
            for (int i=2; i<str.length(); i++) {
                plant += String.valueOf(str.charAt(i));
            }
        }

        String message = new OwnerDAO().appointTask(Integer.parseInt(task), Integer.parseInt(plant));

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(message);
    }
}
