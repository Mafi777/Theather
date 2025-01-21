package bg.tu_varna.sit.AppCommands;

import bg.tu_varna.sit.MainClasses.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommandFreeSeats {
    public void freeseats(String[] inputArray, Storage storage) {
        if (inputArray.length != 3) {
            System.out.println("Usage: freeseats <date> <name>");
            return;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(inputArray[1], formatter);
            String name = inputArray[2];

            storage.showFreeSeats(date, name);

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd-MM-yyyy");
        }
    }
}
