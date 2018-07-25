package personal.davino.j2ee.controller.customersupport;

import personal.davino.j2ee.bean.entity.customersupport.Ticket;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tickets")
public class TicketWebServiceList {
    private List<Ticket> value;

    @XmlElement(name = "ticket")
    public List<Ticket> getValue() {
        return this.value;
    }

    public void setValue(List<Ticket> value) {
        this.value = value;
    }
}
