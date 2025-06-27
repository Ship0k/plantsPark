package by.park.servlet.owner.plants;

import by.park.dao.PlantsDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/owner/addPlant")
public class AddPlantServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String title = req.getParameter("title");
        new PlantsDAO().addPlant(title);
        req.getRequestDispatcher("/plants.html").forward(req, resp);
    }
}
