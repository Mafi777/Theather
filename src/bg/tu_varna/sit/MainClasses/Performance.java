package bg.tu_varna.sit.MainClasses;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "performance")
public class Performance {
    private LocalDate date;
    private String hall;
    private String name;
    private ArrayList<Ticket> reservedTickets;

    public Performance() {
        this.reservedTickets = new ArrayList<>();  // Initialize reservedTickets
    }

    public Performance(LocalDate date, String hall, String name) {
        this.date = date;
        this.hall = hall;
        this.name = name;
        this.reservedTickets = new ArrayList<>();  // Initialize reservedTickets
    }

    @XmlElement(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @XmlElement(name = "hall")
    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTicket(Ticket ticket) {
        this.reservedTickets.add(ticket);
        System.out.println("Successfully added ticket!");
    }
}
