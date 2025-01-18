package bg.tu_varna.sit.MainClasses;

import java.time.LocalDate;

public class Ticket {
    private int row;
    private int seat;
    private LocalDate date;
    private String name;
    private String note;

    public Ticket(int row, int seat, LocalDate date, String name, String note) {
        this.row = row;
        this.seat = seat;
        this.date = date;
        this.name = name;
        this.note = note;
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

    @Override
    public String toString() {
        return "Ticket{" + "row=" + row +", seat=" + seat +", date=" + date +", name='" + name +", note='" + note+'}';
    }
}


