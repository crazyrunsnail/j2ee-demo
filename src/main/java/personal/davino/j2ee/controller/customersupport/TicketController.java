package personal.davino.j2ee.controller.customersupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import personal.davino.j2ee.bean.entity.customersupport.Attachment;
import personal.davino.j2ee.bean.entity.customersupport.Ticket;
import personal.davino.j2ee.bean.entity.customersupport.TicketComment;
import personal.davino.j2ee.bootstrap.annotation.WebController;
import personal.davino.j2ee.repository.customsupport.UserRepository;
import personal.davino.j2ee.service.customersupport.TicketService;
import personal.davino.j2ee.view.DownloadingView;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@WebController
@RequestMapping("ticket")
public class TicketController
{
    private static final Logger log = LoggerFactory.getLogger(TicketController.class);

    @Inject
    TicketService ticketService;

    @Inject
    UserRepository userRepository;

    @RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Map<String, Object> model)
    {
        log.debug("Listing tickets.");
        model.put("tickets", this.ticketService.getAllTickets());

        return "ticket/list";
    }

    @RequestMapping(value = "view/{ticketId}", method = RequestMethod.GET)
    public ModelAndView view(Map<String, Object> model, Pageable page,
                             @PathVariable("ticketId") long ticketId)
    {
        Ticket ticket = this.ticketService.getTicket(ticketId);
        if(ticket == null)
            return this.getListRedirectModelAndView();
        model.put("ticketId", Long.toString(ticketId));
        model.put("ticket", ticket);
        model.put("comments", this.ticketService.getComments(ticketId, page));
        model.put("commentForm", new CommentForm());
        return new ModelAndView("ticket/view");
    }

    @RequestMapping(
            value = "attachment/{attachmentId}",
            method = RequestMethod.GET
    )
    public View download(@PathVariable("attachmentId") long attachmentId)
    {
        Attachment attachment = this.ticketService.getAttachment(attachmentId);
        if(attachment == null)
        {
            log.info("Requested attachment {} not found.", attachmentId);
            return this.getListRedirectView();
        }

        return new DownloadingView(attachment.getName(),
                attachment.getMimeContentType(), attachment.getContents());
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Map<String, Object> model)
    {
        model.put("ticketForm", new TicketForm());
        return "ticket/add";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ModelAndView create(Principal principal, @Valid TicketForm form,
                               Errors errors, Map<String, Object> model)
            throws IOException
    {
        if(errors.hasErrors())
            return new ModelAndView("ticket/add");

        Ticket ticket = new Ticket();
        ticket.setCustomer(userRepository.getByUsername(principal.getName()));
        ticket.setSubject(form.getSubject());
        ticket.setBody(form.getBody());

        for(MultipartFile filePart : form.getAttachments())
        {
            log.debug("Processing attachment for new ticket.");
            Attachment attachment = new Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            if((attachment.getName() != null && attachment.getName().length() > 0) ||
                    (attachment.getContents() != null && attachment.getContents().length > 0))
                ticket.addAttachment(attachment);
        }

        try
        {
            this.ticketService.save(ticket);
        }
        catch(ConstraintViolationException e)
        {
            model.put("validationErrors", e.getConstraintViolations());
            return new ModelAndView("ticket/add");
        }

        return new ModelAndView(new RedirectView(
                "/ticket/view/" + ticket.getId(), true, false
        ));
    }

    @RequestMapping(value = "comment/{ticketId}", method = RequestMethod.POST)
    public ModelAndView comment(Principal principal, @Valid CommentForm form,
                                Errors errors, Map<String, Object> model,
                                Pageable page,
                                @PathVariable("ticketId") long ticketId)
    {
        Ticket ticket = this.ticketService.getTicket(ticketId);
        if(ticket == null)
            return this.getListRedirectModelAndView();

        if(errors.hasErrors())
            return this.view(model, page, ticketId);

        TicketComment comment = new TicketComment();
        comment.setCustomer(this.userRepository.getByUsername(principal.getName()));
        comment.setBody(form.getBody());

        try
        {
            this.ticketService.save(comment, ticketId);
        }
        catch(ConstraintViolationException e)
        {
            model.put("validationErrors", e.getConstraintViolations());
            return this.view(model, page, ticketId);
        }

        return new ModelAndView(new RedirectView(
                "/ticket/view/" + ticketId, true, false
        ));
    }

    private ModelAndView getListRedirectModelAndView()
    {
        return new ModelAndView(this.getListRedirectView());
    }

    private View getListRedirectView()
    {
        return new RedirectView("/ticket/list", true, false);
    }

    public static class TicketForm
    {
        @NotBlank(message = "{validate.ticket.subject}")
        private String subject;
        @NotBlank(message = "{validate.ticket.body}")
        private String body;
        @NotNull(message = "{validate.ticket.attachments}")
        private List<MultipartFile> attachments;

        public String getSubject()
        {
            return subject;
        }

        public void setSubject(String subject)
        {
            this.subject = subject;
        }

        public String getBody()
        {
            return body;
        }

        public void setBody(String body)
        {
            this.body = body;
        }

        public List<MultipartFile> getAttachments()
        {
            return attachments;
        }

        public void setAttachments(List<MultipartFile> attachments)
        {
            this.attachments = attachments;
        }
    }

    public static class CommentForm
    {
        @NotBlank(message = "{validate.ticket.comment.body}")
        private String body;

        public String getBody()
        {
            return body;
        }

        public void setBody(String body)
        {
            this.body = body;
        }
    }
}
