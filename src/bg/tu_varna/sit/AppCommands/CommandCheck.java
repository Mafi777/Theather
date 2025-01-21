package bg.tu_varna.sit.AppCommands;

import bg.tu_varna.sit.MainClasses.Storage;
import bg.tu_varna.sit.MainClasses.Ticket;

public class CommandCheck {
    public void check(String[] inputArray, Storage storage) {
        if (inputArray.length != 2) {
            System.out.println("Usage: check <code>");
            return;
        }

        String code = inputArray[1];
        Ticket ticket = storage.checkTicket(code);

        if (ticket != null) {
            System.out.println("Valid ticket found:");
            System.out.println("Performance: " + ticket.getName());
            System.out.println("Date: " + ticket.getDate());
            System.out.println("Row: " + ticket.getRow());
            System.out.println("Seat: " + ticket.getSeat());
            System.out.println("Status: " + ticket.getStatus());
            System.out.println("Note: " + ticket.getNote());
        } else {
            System.out.println("Invalid ticket code!");
        }
    }
}
