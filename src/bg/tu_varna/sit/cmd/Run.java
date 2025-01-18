package bg.tu_varna.sit.cmd;

import bg.tu_varna.sit.AppCommands.CommandAddEvent;
import bg.tu_varna.sit.FileCommands.CommandHelp;
import bg.tu_varna.sit.FileCommands.CommandOpen;
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


                case "addEvent":
                    CommandAddEvent  commandAddEvent = new CommandAddEvent();
                    commandAddEvent.addEvent(inputArray, storage);
                    break;

                default:
                    System.out.println("Invalid command. Type 'help' for available commands.");
                    break;
            }
        }

        scanner.close();
    }
}
