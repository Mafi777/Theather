package bg.tu_varna.sit.MainClasses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;

public class Performance {
    private LocalDate date;
    private String hall;
    private String name;
    private ArrayList<Ticket> tickets;

    public Performance() {
        this.tickets = new ArrayList<>();
    }

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @XmlElement
    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "ticket")
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        if (this.tickets == null) {
            this.tickets = new ArrayList<>();
        }
        this.tickets.add(ticket);
        System.out.println("Successfully added ticket!");
    }
    public boolean isSeatTaken(int row, int seat) {
        if (tickets == null) return false;

        for (Ticket ticket : tickets) {
            if (ticket.getRow() == row && ticket.getSeat() == seat) {
                return true;
            }
        }
        return false;
    }
    public boolean removeTicket(int row, int seat) {
        if (tickets == null) {
            return false;
        }

        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            if (ticket.getRow() == row && ticket.getSeat() == seat) {
                tickets.remove(i);
                System.out.println("Ticket removed successfully");
                return true;
            }
        }
        System.out.println("No ticket found for row " + row + " and seat " + seat);
        return false;
    }
}