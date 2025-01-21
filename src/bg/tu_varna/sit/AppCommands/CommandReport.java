package bg.tu_varna.sit.AppCommands;

import bg.tu_varna.sit.MainClasses.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommandReport {
    public void report(String[] inputArray, Storage storage) {
        if (inputArray.length < 3 || inputArray.length > 4) {
            System.out.println("Usage: report <from> <to> [<hall>]");
            return;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate fromDate = LocalDate.parse(inputArray[1], formatter);
            LocalDate toDate = LocalDate.parse(inputArray[2], formatter);

            String hallName = null;
            if (inputArray.length == 4) {
                hallName = "Hall " + inputArray[3];
            }

            storage.generateReport(fromDate, toDate, hallName);

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd-MM-yyyy");
        }
    }
}
