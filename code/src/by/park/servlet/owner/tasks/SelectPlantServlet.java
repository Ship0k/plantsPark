package by.park.servlet.owner.tasks;

import by.park.dao.OwnerDAO;
import by.park.entity.Plant;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/owner/selectPlant")
public class SelectPlantServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Plant> listPlant = new OwnerDAO().selectPlant();

        Gson gson = new Gson();
        String json = gson.toJson(listPlant);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
