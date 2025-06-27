package by.park.dao;

import by.park.connect.SQLServer;
import by.park.entity.ListOwnerTask;

import static by.park.resources.SQLManager.getProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * The description of the methods is in the implemented interface
 */
public class ForesterDAO implements Forester {
    private static ListOwnerTask dao(ResultSet resultSet) throws SQLException{
        ListOwnerTask listOwnerTask = new ListOwnerTask();
        listOwnerTask.setId(resultSet.getInt("Id"));
        listOwnerTask.setPlant(resultSet.getString("Plant"));
        listOwnerTask.setTask(resultSet.getString("TaskType"));
        listOwnerTask.setReport(resultSet.getString("Report"));
        return listOwnerTask;
    }

    //for methods performTask and cancelTask
    private static ListOwnerTask report(String sql, int idTask){
        ListOwnerTask listOwnerTask = new ListOwnerTask();
        String ret = getProperty("returnTask");
        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             PreparedStatement returnTask = connection.prepareStatement(ret))
        {
            preparedStatement.setInt(1, idTask);
            preparedStatement.executeUpdate();

            returnTask.setInt(1, idTask);
            ResultSet resultSet = returnTask.executeQuery();
            if (resultSet.next()) {
                listOwnerTask = dao(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOwnerTask;
    }

    @Override
    public ArrayList<ListOwnerTask> viewTasks() {
        ArrayList<ListOwnerTask> array = new ArrayList<>();
        String sql = getProperty("viewTasks");

        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next()) {
                ListOwnerTask listOwnerTask = dao(resultSet);
                array.add(listOwnerTask);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }

    @Override
    public ListOwnerTask performTask(int idTask) {
        String sql = getProperty("performTask");
        return report(sql, idTask);
    }

    @Override
    public ListOwnerTask cancelTask(int idTask) {
        String sql = getProperty("cancelTask");
        return report(sql, idTask);
    }
}
