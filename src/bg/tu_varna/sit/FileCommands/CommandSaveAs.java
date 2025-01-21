package bg.tu_varna.sit.FileCommands;

import bg.tu_varna.sit.MainClasses.Storage;
import javax.xml.bind.JAXBException;

public class CommandSaveAs {
    public void saveAs(String[] inputArray, Storage storage) {
        if (inputArray.length != 2) {
            System.out.println("Usage: saveas <file>");
            return;
        }

        try {
            storage.saveToFile(inputArray[1]);
            System.out.println("Successfully saved as " + inputArray[1]);
        } catch (JAXBException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
