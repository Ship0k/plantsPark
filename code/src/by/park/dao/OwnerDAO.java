package by.park.dao;

import by.park.connect.SQLServer;
import by.park.entity.Plant;
import static by.park.resources.SQLManager.getProperty;

import java.sql.*;
import java.util.ArrayList;

/*
 * The description of the methods is in the implemented interface
 */
public class OwnerDAO implements Owner {
    @Override
    public void confirmExecution(int idTask) {
        String extractSql = getProperty("extractSql");
        String landingSql = getProperty("landingSql");
        String artSql = getProperty("artSql");
        String treatmentSql = getProperty("treatmentSql");
        String destructionSql = getProperty("destructionSql");
        String removeTask = getProperty("removeTask");
        try (Connection connection = SQLServer.createConnection();
             PreparedStatement extract = connection.prepareStatement(extractSql)) {
            extract.setInt(1, idTask);
            ResultSet resultSet = extract.executeQuery();

            if (resultSet.next()) {
                int plantId = resultSet.getInt("PlantId");
                int taskId = resultSet.getInt("TaskId");

                if (taskId == 1) {
                    PreparedStatement landing = connection.prepareStatement(landingSql);
                    landing.setInt(1, plantId);
                    landing.executeUpdate();
                    landing.close();
                } else if (taskId == 2) {
                    PreparedStatement art = connection.prepareStatement(artSql);
                    art.setInt(1, plantId);
                    art.executeUpdate();
                    art.close();
                } else if (taskId == 3) {
                    PreparedStatement treatment = connection.prepareStatement(treatmentSql);
                    treatment.setInt(1, plantId);
                    treatment.executeUpdate();
                    treatment.close();
                } else if (taskId == 4) {
                    PreparedStatement delete = connection.prepareStatement(destructionSql);
                    delete.setInt(1, plantId);
                    delete.executeUpdate();
                    delete.close();
                }
                PreparedStatement remove = connection.prepareStatement(removeTask);
                remove.setInt(1, idTask);
                remove.executeUpdate();
                remove.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Plant> selectPlant() {
        ArrayList<Plant> listPlant = new ArrayList<>();
        String sql = getProperty("selectPlant");
        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Plant plant = new Plant();
                plant.setId(resultSet.getInt("Id"));
                plant.setTitle(resultSet.getString("Title"));
                listPlant.add(plant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPlant;
    }

    @Override
    public String appointTask(int idTask, int idPlant) {
        PlantsDAO plantsInfo = new PlantsDAO();
        Plant plant = plantsInfo.byId(idPlant);
        String message = "Задание добавлено";
        String sql = getProperty("appointTask");
        if (plant.getPlantDetails().getDestructionDate() != null) {
            return message = "Задание отменено, растение " + plant.getTitle() + " под Id=" + plant.getId() + ", уже уичтожено";
        } else if (plant.getPlantDetails().getLandingData() != null && idTask == 1) {
            return message = "Задание отменено, растение " + plant.getTitle() + " под Id=" + plant.getId() + ", уже посажено";
        } else if (plant.getPlantDetails().getLandingData() == null && idTask > 1) {
            return message = "Задание отменено, растение " + plant.getTitle() + " под Id=" + plant.getId() + ", еще не посажено";
        } else {
            try (Connection connection = SQLServer.createConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idTask);
                preparedStatement.setInt(2, idPlant);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return message;
        }
    }

    @Override
    public String deleteTask(int idTask) {
        String message = "Удаление не выполнено";
        String sql = getProperty("deleteTask");
        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idTask);
            preparedStatement.executeUpdate();
            message = "Задание под номером Id=" + idTask + " удалено";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }
}