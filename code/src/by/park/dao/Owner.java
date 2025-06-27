package by.park.dao;

import by.park.entity.Plant;
import java.util.ArrayList;

/*
 * Interface for the park owner, has the following methods:
 * confirmExecution - to confirm the work performed by the forester
 * selectPlant - to select plants of the park with which no work is assigned
 * appointTask - add task for forester
 * deleteTask - delete in case of error when assigning a task
 */
public interface Owner {
    void confirmExecution(int idTask);
    ArrayList<Plant> selectPlant();
    String appointTask(int idTask, int idPlant);
    String deleteTask(int idTask);
}
