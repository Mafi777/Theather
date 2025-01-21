package bg.tu_varna.sit.MainClasses;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum TicketStatus {
    BOOKED,
    BOUGHT
}