package bg.tu_varna.sit.MainClasses;

import java.time.LocalDate;

public class PerformanceStats {
    String name;
    LocalDate date;
    String hallName;
    int totalSeats;
    int boughtTickets;
    int bookedTickets;

    public PerformanceStats(String name, LocalDate date, String hallName, int totalSeats) {
        this.name = name;
        this.date = date;
        this.hallName = hallName;
        this.totalSeats = totalSeats;
        this.boughtTickets = 0;
        this.bookedTickets = 0;
    }
}
