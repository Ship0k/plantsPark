package by.park.entity;

import java.util.Objects;

/*
 * This class contains the names, parts and number according to the database.
 */
public class Plant {
    private int id;
    private String title;
    private PlantDetails plantDetails;

    public Plant(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PlantDetails getPlantDetails() {
        return plantDetails;
    }

    public void setPlantDetails(PlantDetails plantDetails) {
        this.plantDetails = plantDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return id == plant.id &&
                Objects.equals(title, plant.title) &&
                Objects.equals(plantDetails, plant.plantDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, plantDetails);
    }

    @Override
    public String toString() {
        return "Plant - " +
                "id=" + id +
                ", title='" + title + '\'';
    }
}
