package bg.tu_varna.sit.AppCommands;

import bg.tu_varna.sit.MainClasses.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommandUnbook {
    public void unbook(String[] inputArray, Storage storage) {
        if (inputArray.length != 5) {
            System.out.println("Usage: unbook <row> <seat> <date> <name>");
            return;
        }

        try {
            int row = Integer.parseInt(inputArray[1]);
            int seat = Integer.parseInt(inputArray[2]);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(inputArray[3], formatter);
            StringBuilder nameBuilder = new StringBuilder();
            for (int i = 4; i < inputArray.length; i++) {
                nameBuilder.append(inputArray[i].replace("\"", ""));
                if (i < inputArray.length - 1) {
                    nameBuilder.append(" ");
                }
            }
            String name = nameBuilder.toString();

            boolean success = storage.unbookTicket(row, seat, date, name);
            if (success) {
                System.out.println("Successfully unbooked ticket");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid row or seat number");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd-MM-yyyy");
        }
    }
}
