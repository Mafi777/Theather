package bg.tu_varna.sit.AppCommands;

import bg.tu_varna.sit.MainClasses.Performance;
import bg.tu_varna.sit.MainClasses.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommandAddEvent {

    // Command
    public void addEvent(String[] inputArray, Storage storage) {
        if (inputArray.length != 4) {
            System.out.println("Add event command requires 3 arguments: date, hall and name");
            return;

        }
        if (!isValidDateFormat(inputArray[1])) {
            System.out.println("Invalid date format. Please use dd-MM-yyyy");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(inputArray[1], formatter);

        // Validate if a performance already exists in given hall on given date
        if (!storage.CheckHallForFreeDate(inputArray[2], date)) {
            System.out.println("Hall is occupied!");
        }

        // Made new performance with the given data from the user
        Performance performance = new Performance(date, inputArray[2], inputArray[3]);

        // Input the new performance in the given hall

        storage.AddPerformanceToHall(performance, inputArray[2]);


    }
    // inputArray - ["addEvent", "2021-12-12", "1", "Concert"]

    // Helpers
    private boolean isValidDateFormat(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

}

