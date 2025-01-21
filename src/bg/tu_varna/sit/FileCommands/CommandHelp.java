package bg.tu_varna.sit.FileCommands;

public class CommandHelp {
    public void help(){
        System.out.println("The following commands are available:");

        System.out.println("\n1. File commands:");
        System.out.println("open <file>     - opens a file");
        System.out.println("close           - closes the currently opened file");
        System.out.println("save            - saves the current file");
        System.out.println("saveAs <file>   - saves the file as a new file");

        System.out.println("\n2. Event commands:");
        System.out.println("addevent <date> <hall> <name>      - adds a new event");
        System.out.println("freeseats <date> <name>            - shows available seats");


        System.out.println("\n3. Ticket operations:");
        System.out.println("book <row> <seat> <date> <name> <note>  - book a ticket");
        System.out.println("unbook <row> <seat> <date> <name>       - cancel a booking");
        System.out.println("buy <row> <seat> <date> <name>          - buy a ticket");
        System.out.println("check <code>                            - verify ticket by code");


        System.out.println("\n4. Reports and statistics:");
        System.out.println("report <from> <to> [<hall>]    - generates sales report");
        System.out.println("bookings [<date>] [<name>]     - shows current bookings");
        System.out.println("statistics                      - shows attendance statistics");


        System.out.println("\n5. System commands:");
        System.out.println("help    - shows this information");
        System.out.println("exit    - exits the program");
    }
}
