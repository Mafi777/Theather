package bg.tu_varna.sit.MainClasses;

import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {
    private ArrayList<Hall> halls;

    public void setHalls(ArrayList<Hall> halls) {
        this.halls = halls;
    }

    public void AddPerformanceToHall(Performance performance, String hallName){
        Hall hall = HallExists(hallName);
        if (hall == null){
            System.out.println("Hall does not exist!");
            return;
        }

        hall.addPerformance(performance);
    }
    public boolean CheckHallForFreeDate(String hallName, LocalDate date){
        Hall hall = HallExists(hallName);
        if (hall == null){
            return false;
        }

        if (!hall.IsDateFree(date)){
            return false;
        }

        return true;
    }

    private Hall HallExists(String hallName){
        if (halls == null || halls.isEmpty()){
            return null;
        }

        for (Hall hall : halls) {
            if (hall.getHallName().equals(hallName)) {
                return hall;
            }
        }
        return null;
    }
}
