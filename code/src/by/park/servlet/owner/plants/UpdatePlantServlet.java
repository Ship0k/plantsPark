package by.park.servlet.owner.plants;

import by.park.dao.PlantsDAO;
import by.park.entity.Plant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@WebServlet("/owner/updatePlant")
public class UpdatePlantServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
        String json = "";
        if(br != null) {
            json = br.readLine();
        }
        Gson gson = new GsonBuilder().setDateFormat("dd MM yyyy").create();
        Plant receivePlant = gson.fromJson(json, Plant.class);

        Plant curPlants = new PlantsDAO().replacePlant(receivePlant);

        String jsonPlant = gson.toJson(curPlants);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonPlant);
    }
}
