package bg.tu_varna.sit.cmd;

import bg.tu_varna.sit.AppCommands.*;
import bg.tu_varna.sit.FileCommands.*;
import bg.tu_varna.sit.MainClasses.Storage;

import java.util.Scanner;

public class Run {
    public void exec() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

       Storage storage = new Storage();

        while (running) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();
            String[] inputArray = input.split("\\s+");

            switch (inputArray[0].toLowerCase()) {
                case "exit":
                    System.out.println("Exiting program...");
                    running = false;
                    break;

                case "help":
                    CommandHelp commandHelp = new CommandHelp();
                    commandHelp.help();
                    break;
                case "open":
                    CommandOpen commandOpen = new CommandOpen();
                    commandOpen.OpenFile(inputArray, storage);
                    break;


                case "addevent":
                    CommandAddEvent commandAddEvent = new CommandAddEvent();
                    commandAddEvent.addEvent(inputArray, storage);
                    break;

                case "save":
                    CommandSave commandSave = new CommandSave();
                    commandSave.save(inputArray, storage);
                    break;

                case "saveas":
                    CommandSaveAs commandSaveAs = new CommandSaveAs();
                    commandSaveAs.saveAs(inputArray, storage);
                    break;

                case "close":
                    CommandClose commandClose = new CommandClose();
                    commandClose.close(storage);
                    break;

                case "freeseats":
                    CommandFreeSeats commandFreeSeats = new CommandFreeSeats();
                    commandFreeSeats.freeseats(inputArray, storage);
                    break;

                case "book":
                    CommandBook commandBook = new CommandBook();
                    commandBook.book(inputArray, storage);
                    break;

                case "unbook":
                    CommandUnbook commandUnbook = new CommandUnbook();
                    commandUnbook.unbook(inputArray, storage);
                    break;

                case "buy":
                    CommandBuy commandBuy = new CommandBuy();
                    commandBuy.buy(inputArray, storage);
                    break;

                case "bookings":
                    CommandBookings commandBookings = new CommandBookings();
                    commandBookings.showBookings(inputArray, storage);
                    break;


                case "check":
                    CommandCheck commandCheck = new CommandCheck();
                    commandCheck.check(inputArray, storage);
                    break;

                case "report":
                        CommandReport commandReport = new CommandReport();
                        commandReport.report(inputArray, storage);
                        break;

                case "statistics":
                    CommandBonusStatistics commandStatistics = new CommandBonusStatistics();
                    commandStatistics.showStatistics(storage);
                    break;
                default:
                    System.out.println("Invalid command. Type 'help' for available commands.");
                    break;
            }
        }

        scanner.close();
    }
}
