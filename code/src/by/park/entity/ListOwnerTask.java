package by.park.entity;

import java.util.Objects;

/*
 * The role of the forester to view the list of tasks and the ability to make your report
 */
public class ListOwnerTask {
    private int id;
    private String plant;
    private String task;
    private String report;

    public ListOwnerTask(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListOwnerTask that = (ListOwnerTask) o;
        return id == that.id &&
                Objects.equals(plant, that.plant) &&
                Objects.equals(task, that.task) &&
                Objects.equals(report, that.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, plant, task, report);
    }

    @Override
    public String toString() {
        return "ListOwnerTask{" +
                "id=" + id +
                ", plant='" + plant + '\'' +
                ", task='" + task + '\'' +
                ", report='" + report + '\'' +
                '}';
    }
}
