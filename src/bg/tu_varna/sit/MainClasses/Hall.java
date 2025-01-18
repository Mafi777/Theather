package bg.tu_varna.sit.MainClasses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;
import java.util.ArrayList;

@XmlType(propOrder = {"hallName", "numberOfSeats", "performances"})
public class Hall {
    private String hallName;
    private int numberOfSeats;
    private ArrayList<Performance> performances;

    public Hall() {
    }

    public Hall(String hallName, int numberOfSeats) {
        this.hallName = hallName;
        this.numberOfSeats = numberOfSeats;
        this.performances = new ArrayList<>();
    }

    public void addPerformance(Performance performance){
        this.performances.add(performance);
    }

    @XmlElement(name = "hallName")
    public String getHallName() {
        return this.hallName;
    }

    public void setHallName(String hallname) {
        this.hallName = hallname;
    }

    @XmlElement(name = "numberOfSeats")
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @XmlElementWrapper(name = "performances")
    @XmlElement(name = "performance")
    public ArrayList<Performance> getPerformances() {
        return performances;
    }
    public boolean IsDateFree(LocalDate date){
        if (performances.isEmpty()){
            return true;
        }

        for (Performance performance : performances) {
            if (performance.getDate().equals(date)){
                return false;
            }
        }

        return true;
    }
}


