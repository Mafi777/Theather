package bg.tu_varna.sit.FileCommands;

import bg.tu_varna.sit.MainClasses.Hall;
import bg.tu_varna.sit.MainClasses.Halls;
import bg.tu_varna.sit.MainClasses.Performance;
import bg.tu_varna.sit.MainClasses.Storage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;

public class CommandOpen {
    @SuppressWarnings("unchecked")
    public void OpenFile(String[] inputArray, Storage storage) {
        if (inputArray.length != 2) {
            System.out.println("Open command requires a file name");
            return;
        }

        File file = new File("C:\\Users\\st\\OneDrive\\Desktop\\Git_repos\\java-theathre\\Theather\\src\\bg\\tu_varna\\sit\\files/" + inputArray[1]);
        if (!file.exists()) {
            System.out.println("File " + inputArray[1] + " does not exist in files directory");
            return;
        }

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Halls.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Halls halls = (Halls) jaxbUnmarshaller.unmarshal(file);

            storage.setHalls(new ArrayList<>(halls.getHalls()));
            System.out.println("Successfully opened " + inputArray[1]);

            for (Hall hall : halls.getHalls()) {
                System.out.println("Hall Name: " + hall.getHallName());
                System.out.println("Number of Seats: " + hall.getNumberOfSeats());

                for (Performance performance : hall.getPerformances()) {
                    System.out.println("Performance Date: " + performance.getDate());
                    System.out.println("Performance Hall: " + performance.getHall());
                    System.out.println("Performance Name: " + performance.getName());
                }
            }

        } catch (JAXBException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}