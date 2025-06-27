package by.park.entity;

import java.sql.Date;
import java.util.Objects;

/*
 * This class complements its parent and stores such parameters as,
 * date of planting, number of treatments, number of artistic treatments,
 * as well as the date of destruction of the plant
 */
public class PlantDetails extends Plant {
    private Date landingData;
    private int treatmentN;
    private int artWorkN;
    private Date destructionDate;

    public PlantDetails() {
    }

    public Date getLandingData() {
        return landingData;
    }

    public void setLandingData(Date landingData) {
        this.landingData = landingData;
    }

    public int getTreatmentN() {
        return treatmentN;
    }

    public void setTreatmentN(int treatmentN) {
        this.treatmentN = treatmentN;
    }

    public int getArtWorkN() {
        return artWorkN;
    }

    public void setArtWorkN(int artWorkN) {
        this.artWorkN = artWorkN;
    }

    public Date getDestructionDate() {
        return destructionDate;
    }

    public void setDestructionDate(Date destruction) {
        this.destructionDate = destruction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PlantDetails that = (PlantDetails) o;
        return treatmentN == that.treatmentN &&
                artWorkN == that.artWorkN &&
                Objects.equals(landingData, that.landingData) &&
                Objects.equals(destructionDate, that.destructionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), landingData, treatmentN, artWorkN, destructionDate);
    }

    @Override
    public String toString() {
        return "{landingData=" + landingData +
                ", treatmentN=" + treatmentN +
                ", artWorkN=" + artWorkN +
                ", destruction=" + destructionDate + '}';
    }
}
