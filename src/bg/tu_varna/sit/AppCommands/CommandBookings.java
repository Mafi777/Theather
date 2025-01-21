package bg.tu_varna.sit.AppCommands;

import bg.tu_varna.sit.MainClasses.Storage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommandBookings {
    public void showBookings(String[] inputArray, Storage storage) {
        if (inputArray.length > 3) {
            System.out.println("Usage: bookings [<date>] [<name>]");
            return;
        }

        LocalDate date = null;
        String name = null;

        try {
            if (inputArray.length >= 2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                date = LocalDate.parse(inputArray[1], formatter);
            }
            if (inputArray.length == 3) {
                name = inputArray[2];
            }

            storage.showBookings(date, name);

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd-MM-yyyy");
        }
    }
}