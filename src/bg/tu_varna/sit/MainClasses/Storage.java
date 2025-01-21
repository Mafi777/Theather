package bg.tu_varna.sit.MainClasses;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {
    private ArrayList<Hall> halls;
    private String currentFileName;

    public Storage() {
        this.halls = new ArrayList<>();
    }

    public void setHalls(ArrayList<Hall> halls) {
        this.halls = halls;
    }
    public void clear() {
        halls = new ArrayList<>();
        currentFileName = null;
    }

    public ArrayList<Hall> getHalls() {
        return halls;
    }

    public void AddPerformanceToHall(Performance performance, String hallName){
        Hall hall = HallExists(hallName);
        if (hall == null){
            System.out.println("Hall does not exist!");
            return;
        }

        hall.addPerformance(performance);
    }
    public boolean CheckHallForFreeDate(String hallName, LocalDate date) {
        Hall hall = HallExists(hallName);
        if (hall == null){
            return false;
        }

        if (!hall.IsDateFree(date)){
            return false;
        }

        return true;
    }

    private Hall HallExists(String hallName) {
        if (halls == null || halls.isEmpty()) {
            return null;
        }

        for (Hall hall : halls) {
            if (hall.getHallName().equals(hallName)) {
                return hall;
            }
        }
        System.out.println("Hall " + hallName + " does not exist!");
        return null;
    }
    public void saveToFile(String filename) throws JAXBException {
        File directory = new File("files");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File file = new File("C:\\Users\\st\\OneDrive\\Desktop\\Git_repos\\java-theathre\\Theather\\src\\bg\\tu_varna\\sit\\files/" + filename);
        Halls hallsWrapper = new    Halls();
        hallsWrapper.setHalls(halls);

        JAXBContext context = JAXBContext.newInstance(Halls.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(hallsWrapper, file);
    }

    public boolean bookTicket(int row, int seat, LocalDate date, String name, String note, String code) {
        for (Hall hall : halls) {
            for (Performance performance : hall.getPerformances()) {
                if (performance.getName().equals(name) && performance.getDate().equals(date)) {
                    Ticket ticket = new Ticket(row, seat, date, name, note);
                    performance.addTicket(ticket);
                    return true;
                }
            }
        }
        System.out.println("Performance not found for date: " + date);
        return false;
    }
    public String getCurrentFileName() {
        return currentFileName;
    }

    public void setCurrentFileName(String fileName) {
        this.currentFileName = fileName;
    }

    private Performance findPerformance(String dateStr, String name) {
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        for (Hall hall : halls) {
            for (Performance performance : hall.getPerformances()) {
                if (performance.getDate().equals(date) && performance.getName().equals(name)) {
                    return performance;
                }
            }
        }
        return null;
    }
    public void showFreeSeats(LocalDate date, String name) {
        Performance performance = findPerformanceByDateAndName(date, name);
        if (performance == null) {
            System.out.println("Performance not found");
            return;
        }

        Hall hall = findHallByName(performance.getHall());
        if (hall == null) {
            System.out.println("Hall not found");
            return;
        }

        System.out.println("Free seats for " + name + " on " + date + ":");
        System.out.println("Hall: " + hall.getHallName() + " (Total seats: " + hall.getNumberOfSeats() + ")");

        int rows = hall.getNumberOfSeats() / 10;
        int seatsPerRow = 10;

        for (int row = 1; row <= rows; row++) {
            System.out.print("Row " + row + ": ");
            for (int seat = 1; seat <= seatsPerRow; seat++) {
                boolean isTaken = performance.isSeatTaken(row, seat);
                if (!isTaken) {
                    System.out.print(seat + " ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    private Performance findPerformanceByDateAndName(LocalDate date, String name) {
        for (Hall hall : halls) {
            for (Performance performance : hall.getPerformances()) {
                if (performance.getDate().equals(date) && performance.getName().equals(name)) {
                    return performance;
                }
            }
        }
        return null;
    }

    private Hall findHallByName(String hallName) {
        for (Hall hall : halls) {
            if (hall.getHallName().equals(hallName)) {
                return hall;
            }
        }
        return null;
    }
    public boolean unbookTicket(int row, int seat, LocalDate date, String name) {
        for (Hall hall : halls) {
            for (Performance performance : hall.getPerformances()) {
                if (performance.getName().equals(name) && performance.getDate().equals(date)) {
                    for (Ticket ticket : performance.getTickets()) {
                        if (ticket.getRow() == row && ticket.getSeat() == seat) {
                            if (ticket.getStatus() == TicketStatus.BOOKED) {
                                performance.getTickets().remove(ticket);
                                return true;
                            } else {
                                System.out.println("Ticket is already bought and cannot be unbooked");
                                return false;
                            }
                        }
                    }
                    System.out.println("No ticket found for this seat");
                    return false;
                }
            }
        }
        System.out.println("Performance not found for date: " + date);
        return false;
    }
    public String buyTicket(int row, int seat, LocalDate date, String name) {
        for (Hall hall : halls) {
            for (Performance performance : hall.getPerformances()) {
                if (performance.getName().equals(name) && performance.getDate().equals(date)) {
                    if (performance.isSeatTaken(row, seat)) {
                        System.out.println("This seat is already taken!");
                        return null;
                    }

                    Ticket ticket = new Ticket(row, seat, date, name, "");
                    ticket.setStatus(TicketStatus.BOUGHT);
                    performance.addTicket(ticket);
                    return ticket.getCode();
                }
            }
        }
        System.out.println("Performance not found for date: " + date);
        return null;
    }
    public Ticket checkTicket(String code) {
        for (Hall hall : halls) {
            for (Performance performance : hall.getPerformances()) {
                for (Ticket ticket : performance.getTickets()) {
                    if (ticket.getCode().equals(code)) {
                        return ticket;
                    }
                }
            }
        }
        return null;
    }

    public void generateReport(LocalDate fromDate, LocalDate toDate, String hallName) {
        boolean foundAny = false;
        int totalTickets = 0;
        double totalIncome = 0;

        System.out.println("Report from " + fromDate + " to " + toDate);
        if (hallName != null) {
            System.out.println("For hall: " + hallName);
        }
        System.out.println("----------------------------------------");

        for (Hall hall : halls) {
            if (hallName != null && !hall.getHallName().equals(hallName)) {
                continue;
            }

            for (Performance performance : hall.getPerformances()) {
                LocalDate performanceDate = performance.getDate();

                if (!performanceDate.isBefore(fromDate) && !performanceDate.isAfter(toDate)) {
                    int boughtTickets = 0;
                    for (Ticket ticket : performance.getTickets()) {
                        if (ticket.getStatus() == TicketStatus.BOUGHT) {
                            boughtTickets++;
                        }
                    }

                    if (boughtTickets > 0) {
                        foundAny = true;
                        System.out.println("Hall: " + hall.getHallName());
                        System.out.println("Performance: " + performance.getName());
                        System.out.println("Date: " + performanceDate);
                        System.out.println("Sold tickets: " + boughtTickets);
                        System.out.println("----------------------------------------");

                        totalTickets += boughtTickets;
                    }
                }
            }
        }

        if (!foundAny) {
            System.out.println("No tickets sold in this period.");
        } else {
            System.out.println("Total tickets sold: " + totalTickets);
        }
    }

    public void showBookings(LocalDate date, String name) {
        boolean foundAny = false;
        System.out.println("Booked (unpaid) tickets:");
        System.out.println("----------------------------------------");

        for (Hall hall : halls) {
            for (Performance performance : hall.getPerformances()) {

                if (date != null && !performance.getDate().equals(date)) {
                    continue;
                }
                if (name != null && !performance.getName().equals(name)) {
                    continue;
                }

                boolean hasBookings = false;
                StringBuilder performanceInfo = new StringBuilder();
                performanceInfo.append("Performance: ").append(performance.getName()).append("\n");
                performanceInfo.append("Date: ").append(performance.getDate()).append("\n");
                performanceInfo.append("Hall: ").append(hall.getHallName()).append("\n");
                performanceInfo.append("Booked seats:\n");

                for (Ticket ticket : performance.getTickets()) {
                    if (ticket.getStatus() == TicketStatus.BOOKED) {
                        hasBookings = true;
                        foundAny = true;
                        performanceInfo.append(String.format("  Row: %d, Seat: %d, Note: %s%n",
                                ticket.getRow(),
                                ticket.getSeat(),
                                ticket.getNote()));
                    }
                }

                if (hasBookings) {
                    System.out.println(performanceInfo);
                    System.out.println("----------------------------------------");
                }
            }
        }

        if (!foundAny) {
            if (date != null && name != null) {
                System.out.println("No bookings found for " + name + " on " + date);
            } else if (date != null) {
                System.out.println("No bookings found for date: " + date);
            } else if (name != null) {
                System.out.println("No bookings found for: " + name);
            } else {
                System.out.println("No bookings found");
            }
        }
    }
    public void generateViewershipStatistics() {
        System.out.println("Performance Viewership Statistics");
        System.out.println("----------------------------------------");


        Map<String, PerformanceStats> statsMap = new HashMap<>();


        for (Hall hall : halls) {
            for (Performance performance : hall.getPerformances()) {
                String performanceKey = performance.getName() + " on " + performance.getDate();

                PerformanceStats stats = statsMap.getOrDefault(performanceKey,
                        new PerformanceStats(performance.getName(),
                                performance.getDate(),
                                hall.getHallName(),
                                hall.getNumberOfSeats()));


                for (Ticket ticket : performance.getTickets()) {
                    if (ticket.getStatus() == TicketStatus.BOUGHT) {
                        stats.boughtTickets++;
                    } else if (ticket.getStatus() == TicketStatus.BOOKED) {
                        stats.bookedTickets++;
                    }
                }

                statsMap.put(performanceKey, stats);
            }
        }


        List<PerformanceStats> sortedStats = new ArrayList<>(statsMap.values());
        sortedStats.sort((a, b) -> b.boughtTickets - a.boughtTickets);


        for (PerformanceStats stats : sortedStats) {
            double occupancyRate = (double)(stats.boughtTickets + stats.bookedTickets) / stats.totalSeats * 100;

            System.out.println("Performance: " + stats.name);
            System.out.println("Date: " + stats.date);
            System.out.println("Hall: " + stats.hallName);
            System.out.println("Bought tickets: " + stats.boughtTickets);
            System.out.println("Booked tickets: " + stats.bookedTickets);
            System.out.println(String.format("Occupancy rate: %.2f%%", occupancyRate));
            System.out.println("----------------------------------------");
        }
    }


}
