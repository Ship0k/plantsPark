package by.park.dao;

import by.park.entity.ListOwnerTask;

import java.util.ArrayList;

/*
 * The forester interface declares the following methods:
 *
 * viewTask - view all task list
 * performTask - report on completed assignment
 * cancelTask - cancel the task in case of an error
 */
public interface Forester {
    ArrayList<ListOwnerTask> viewTasks();
    ListOwnerTask performTask(int idTask);
    ListOwnerTask cancelTask(int idTask);
}
