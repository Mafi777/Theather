package bg.tu_varna.sit.AppCommands;

import bg.tu_varna.sit.MainClasses.Performance;
import bg.tu_varna.sit.MainClasses.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommandAddEvent {
    public void addEvent(String[] inputArray, Storage storage) {
        if (inputArray.length < 4) {
            System.out.println("Usage: addevent <date> <hall_number> <name>");
            return;
        }

        if (!isValidDateFormat(inputArray[1])) {
            System.out.println("Invalid date format. Please use dd-MM-yyyy");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(inputArray[1], formatter);
        String hall = "Hall " + inputArray[2];

        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 3; i < inputArray.length; i++) {
            nameBuilder.append(inputArray[i].replace("\"", ""));
            if (i < inputArray.length - 1) {
                nameBuilder.append(" ");
            }
        }
        String name = nameBuilder.toString();

        if (!storage.CheckHallForFreeDate(hall, date)) {
            System.out.println("Hall is occupied for this date!");
            return;
        }

        Performance performance = new Performance();
        performance.setDate(date);
        performance.setHall(hall);
        performance.setName(name);

        storage.AddPerformanceToHall(performance, hall);
        System.out.println("Successfully added event: " + name + " in " + hall + " on " + date);
    }


    private boolean isValidDateFormat(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}