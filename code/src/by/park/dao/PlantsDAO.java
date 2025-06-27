package by.park.dao;

import by.park.connect.SQLServer;
import by.park.entity.Plant;
import by.park.entity.PlantDetails;
import static by.park.resources.SQLManager.getProperty;

import java.sql.*;
import java.util.ArrayList;

/*
 * The description of the methods is in the implemented interface
 */
public class PlantsDAO implements Plants {
    private static Plant dao(ResultSet resultSet) throws SQLException {
        Plant plant = new Plant();
        PlantDetails pd = new PlantDetails();

        plant.setId(resultSet.getInt("Id"));
        plant.setTitle(resultSet.getString("Title"));
        pd.setLandingData(resultSet.getDate("LandingDate"));
        pd.setArtWorkN(resultSet.getInt("ArtWorkN"));
        pd.setTreatmentN(resultSet.getInt("TreatmentN"));
        pd.setDestructionDate(resultSet.getDate("DestructionDate"));

        plant.setPlantDetails(pd);
        return plant;
    }

    //for methods plantPrev and plantNext
    private static Plant turn(Plant plant, String sql) {
        Plant plantNew = new Plant();
        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, plant.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                plantNew = dao(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plantNew;
    }

    @Override
    public Plant plantTopId() {
        Plant plant = new Plant();
        String sql = getProperty("plantTopId");

        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                plant = dao(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plant;
    }

    @Override
    public Plant plantPrev(Plant plant) {
        String sql = getProperty("plantPrev");
        return turn(plant, sql);
    }

    @Override
    public Plant plantNext(Plant plant) {
        String sql = getProperty("plantNext");
        return turn(plant, sql);
    }

    @Override
    public Plant byId(int id) {
        Plant plant = new Plant();
        String sql = getProperty("byId");

        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                plant = dao(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plant;
    }

    @Override
    public ArrayList<Plant> viewPlants() {
        ArrayList<Plant> plants = new ArrayList<>();
        String sql = getProperty("viewPlants");
        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Plant plant = dao(resultSet);
                plants.add(plant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plants;
    }

    @Override
    public String deletePlant(int idPlant) {
        String message = "Удаление не выполнено";
        String sql = getProperty("deletePlant");
        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idPlant);
            preparedStatement.executeUpdate();
            message = "Растение под номером Id=" + idPlant + " удалено";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public void addPlant(String title) {
        String sql = getProperty("addSql");
        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Plant replacePlant(Plant plant) {
        String sql = getProperty("replacePlant");
        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, plant.getTitle());
            preparedStatement.setInt(2, plant.getId());
            preparedStatement.setDate(3, plant.getPlantDetails().getLandingData());
            preparedStatement.setInt(4, plant.getId());
            preparedStatement.setInt(5, plant.getPlantDetails().getArtWorkN());
            preparedStatement.setInt(6, plant.getId());
            preparedStatement.setInt(7, plant.getPlantDetails().getTreatmentN());
            preparedStatement.setInt(8, plant.getId());
            preparedStatement.setDate(9, plant.getPlantDetails().getDestructionDate());
            preparedStatement.setInt(10, plant.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return byId(plant.getId());
    }
}
