package bg.tu_varna.sit.MainClasses;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class Ticket {
    @XmlElement
    private int row;

    @XmlElement
    private int seat;

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate date;

    @XmlElement
    private String name;

    @XmlElement
    private String note;

    @XmlElement
    private String code;

    @XmlElement
    private TicketStatus status;

    public Ticket() {
    }

    public Ticket(int row, int seat, LocalDate date, String name, String note) {
        this.row = row;
        this.seat = seat;
        this.date = date;
        this.name = name;
        this.note = note;
        this.status = TicketStatus.BOOKED;
        generateCode();
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private void generateCode() {
        this.code = String.format("%d-%d-%s-%s",
                row,
                seat,
                date.toString(),
                java.util.UUID.randomUUID().toString().substring(0, 8));
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "row=" + row +
                ", seat=" + seat +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", code='" + code + '\'' +
                ", status=" + status +
                '}';
    }
}