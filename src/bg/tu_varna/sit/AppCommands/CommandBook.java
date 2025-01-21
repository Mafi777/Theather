package bg.tu_varna.sit.AppCommands;

import bg.tu_varna.sit.MainClasses.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommandBook {
    public void book(String[] inputArray, Storage storage) {
        if (inputArray.length < 6) {
            System.out.println("Usage: book <row> <seat> <date> <name> <note>");
            return;
        }

        try {
            int row = Integer.parseInt(inputArray[1]);
            int seat = Integer.parseInt(inputArray[2]);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(inputArray[3], formatter);


            StringBuilder nameBuilder = new StringBuilder();
            for (int i = 4; i < inputArray.length - 1; i++) {
                nameBuilder.append(inputArray[i].replace("\"", ""));
                if (i < inputArray.length - 2) {
                    nameBuilder.append(" ");
                }
            }
            String name = nameBuilder.toString();
            String note = inputArray[inputArray.length - 1];

            boolean success = storage.bookTicket(row, seat, date, name, note, "");
            if (success) {
                System.out.println("Successfully booked ticket");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid row or seat number");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd-MM-yyyy");
        }
    }
}