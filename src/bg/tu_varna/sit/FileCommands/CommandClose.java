package bg.tu_varna.sit.FileCommands;

import bg.tu_varna.sit.MainClasses.Storage;

public class CommandClose {
    public  void close(Storage storage){
        storage.clear();
        System.out.println("Successfully closed the file.");
    }
}
