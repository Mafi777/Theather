package bg.tu_varna.sit.MainClasses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "halls")
public class Halls {
    private ArrayList<Hall> halls;

    public Halls() {
        this.halls = new ArrayList<>();
    }

    @XmlElement(name = "hall")
    public ArrayList<Hall> getHalls() {
        return halls;
    }

    public void setHalls(ArrayList<Hall> halls) {
        this.halls = halls;
    }
}