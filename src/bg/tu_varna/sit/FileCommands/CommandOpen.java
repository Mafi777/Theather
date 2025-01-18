package bg.tu_varna.sit.FileCommands;

import bg.tu_varna.sit.MainClasses.Hall;
import bg.tu_varna.sit.MainClasses.Halls;
import bg.tu_varna.sit.MainClasses.Performance;
import bg.tu_varna.sit.MainClasses.Storage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;

public class CommandOpen {
    @SuppressWarnings("unchecked")
    public void OpenFile(String[] inputArray, Storage storage) {
        try {
            File file = new File(inputArray[1]);
            JAXBContext jaxbContext = JAXBContext.newInstance(Halls.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Halls halls = (Halls) jaxbUnmarshaller.unmarshal(file);

            // Access the data
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
            e.printStackTrace();
        }
    }
}