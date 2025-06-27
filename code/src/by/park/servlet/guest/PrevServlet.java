package by.park.servlet.guest;

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

@WebServlet("/prev")
public class PrevServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String jsonBr = "";
        if (br != null) {
            jsonBr = br.readLine();
        }
        Gson gson = new GsonBuilder().setDateFormat("dd MM yyyy").create();

        Plant plant = gson.fromJson(jsonBr, Plant.class);
        Plant curPlant = new PlantsDAO().plantPrev(plant);

        String json = gson.toJson(curPlant);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}