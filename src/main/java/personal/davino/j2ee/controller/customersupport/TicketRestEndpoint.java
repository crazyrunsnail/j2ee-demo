package personal.davino.j2ee.controller.customersupport;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import personal.davino.j2ee.bean.entity.customersupport.Attachment;
import personal.davino.j2ee.bean.entity.customersupport.Ticket;
import personal.davino.j2ee.bootstrap.annotation.RestEndpoint;
import personal.davino.j2ee.exception.ResourceNotFoundException;
import personal.davino.j2ee.service.customersupport.TicketService;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@RestEndpoint
public class TicketRestEndpoint {
    @Inject
    TicketService ticketService;

    @RequestMapping(value = "ticket", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> discover() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "OPTIONS,HEAD,GET,POST");
        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "ticket/{id}", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> discover(@PathVariable("id") long id) {
        if (this.ticketService.getTicket(id) == null)
            throw new ResourceNotFoundException();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "OPTIONS,HEAD,GET,DELETE");
        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "ticket", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public TicketWebServiceList read() {
        TicketWebServiceList list = new TicketWebServiceList();
        list.setValue(this.ticketService.getAllTickets());
        return list;
    }

    @RequestMapping(value = "ticket/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Ticket read(@PathVariable("id") long id) {
        Ticket ticket = this.ticketService.getTicket(id);
        if (ticket == null)
            throw new ResourceNotFoundException();
        return ticket;
    }

    @RequestMapping(value = "ticket", method = RequestMethod.POST)
    public ResponseEntity<Ticket> create(@RequestBody TicketForm form) {
        Ticket ticket = new Ticket();
        ticket.setCustomer(null); // TODO: How do you secure REST?
        ticket.setSubject(form.getSubject());
        ticket.setBody(form.getBody());
        ticket.setAttachments(form.getAttachments());

        this.ticketService.save(ticket);

        String uri = ServletUriComponentsBuilder.fromCurrentServletMapping()
                .path("/ticket/{id}").buildAndExpand(ticket.getId()).toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(ticket, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "ticket/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        if (this.ticketService.getTicket(id) == null)
            throw new ResourceNotFoundException();
        this.ticketService.deleteTicket(id);
    }

    @XmlRootElement(name = "ticket")
    public static class TicketForm {
        private String subject;
        private String body;
        private List<Attachment> attachments;

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        @XmlElement(name = "attachment")
        public List<Attachment> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<Attachment> attachments) {
            this.attachments = attachments;
        }
    }
}
