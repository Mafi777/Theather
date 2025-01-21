package bg.tu_varna.sit.MainClasses;

import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDate;
import java.util.ArrayList;

public class Hall {
    private String hallName;
    private int numberOfSeats;
    private ArrayList<Performance> performances;

    public Hall() {
        this.performances = new ArrayList<>();
    }

    @XmlElement(name = "hallName")
    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    @XmlElement(name = "numberOfSeats")
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @XmlElement(name = "performance")
    public ArrayList<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(ArrayList<Performance> performances) {
        this.performances = performances;
    }

    public void addPerformance(Performance performance) {
        if(this.performances == null) {
            this.performances = new ArrayList<>();
        }
        this.performances.add(performance);
    }

    public boolean IsDateFree(LocalDate date) {
        if (performances == null || performances.isEmpty()) {
            return true;
        }

        for (Performance performance : performances) {
            if (performance.getDate().equals(date)) {
                return false;
            }
        }
        return true;
    }
}