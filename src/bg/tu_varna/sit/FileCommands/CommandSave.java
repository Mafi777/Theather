package bg.tu_varna.sit.FileCommands;

import bg.tu_varna.sit.MainClasses.Halls;
import bg.tu_varna.sit.MainClasses.Storage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class CommandSave {
    public void save(String[] inputArray, Storage storage){
        try {
            Halls halls = new Halls();
            halls.setHalls(storage.getHalls());

            JAXBContext context = JAXBContext.newInstance(Halls.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(halls, new File("C:\\Users\\st\\OneDrive\\Desktop\\Git_repos\\java-theathre\\Theather\\src\\bg\\tu_varna\\sit\\files//file1.xml"));
            System.out.println("Successfully saved changes to file1.xml");

        } catch (JAXBException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
