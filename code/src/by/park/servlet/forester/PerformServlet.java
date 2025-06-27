package by.park.servlet.forester;

import by.park.dao.ForesterDAO;
import by.park.entity.ListOwnerTask;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet("/forester/perform")
public class PerformServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String id = "";
        if (br != null) {
            id = br.readLine();
        }

        ListOwnerTask curListOwnerTask = new ForesterDAO().performTask(Integer.parseInt(id));

        Gson gson = new Gson();
        String json = gson.toJson(curListOwnerTask);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
