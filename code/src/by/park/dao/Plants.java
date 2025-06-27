package by.park.dao;

import by.park.entity.Plant;

import java.util.ArrayList;

/*
 * Interface for all actions performed on the plants of the park(owner and guest):
 *
 * plantTopId - plant start information
 * plantPrev and plantNext - for buttons forward and backward in the list of plants
 * byId - search for a plant by its number ID
 *
 * viewPlants - method for displaying a complete list of plants
 * deletePlant - remove plants from the database
 * addPlant - add a new plant to the database
 * replacePlant - save all actions in the database after editing the plant
 */
public interface Plants {
    //for guests
    Plant plantTopId();
    Plant plantPrev(Plant plant);
    Plant plantNext(Plant plant);
    Plant byId(int id);

    //for owner
    ArrayList<Plant> viewPlants();
    String deletePlant(int idPlant);
    void addPlant(String title);
    Plant replacePlant(Plant plant);
}
