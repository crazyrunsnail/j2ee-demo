package personal.davino.j2ee.service.customersupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.davino.j2ee.bean.entity.customersupport.Attachment;
import personal.davino.j2ee.bean.entity.customersupport.Ticket;
import personal.davino.j2ee.bean.entity.customersupport.TicketComment;
import personal.davino.j2ee.repository.customsupport.AttachmentRepository;
import personal.davino.j2ee.repository.customsupport.TicketCommentRepository;
import personal.davino.j2ee.repository.customsupport.TicketRepository;
import personal.davino.j2ee.repository.customsupport.UserRepository;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultTicketService implements TicketService {
    @Inject
    TicketRepository ticketRepository;
    @Inject
    TicketCommentRepository commentRepository;
    @Inject
    AttachmentRepository attachmentRepository;
    @Inject
    UserRepository userRepository;

    @Override
    @Transactional
    public List<Ticket> getAllTickets() {
        List<Ticket> list = new ArrayList<>();
        this.ticketRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    @Transactional
    public Ticket getTicket(long id) {
        Ticket entity = this.ticketRepository.findOne(id);
        entity.getNumberOfAttachments();
        return entity == null ? null : entity;
    }


    @Override
    @Transactional
    public void save(Ticket ticket) {

        if (ticket.getId() < 1) {
            ticket.setDateCreated(Instant.now());
            this.ticketRepository.save(ticket);

        } else
            this.ticketRepository.save(ticket);
    }

    @Override
    @Transactional
    public void deleteTicket(long id) {
        this.ticketRepository.delete(id);
    }

    @Override
    @Transactional
    public Page<TicketComment> getComments(long ticketId, Pageable page) {
        return this.commentRepository.getByTicketId(ticketId, page);

    }



    @Override
    @Transactional
    public void save(TicketComment comment, long ticketId) {
        if (comment.getId() < 1) {
            comment.setDateCreated(Instant.now());
            this.commentRepository.save(comment);
        } else
            this.commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteComment(long id) {
        this.commentRepository.delete(id);
    }

    @Override
    @Transactional
    public Attachment getAttachment(long id) {
        Attachment attachment = this.attachmentRepository.findOne(id);
        if(attachment != null)
            attachment.getContents();
        return attachment;
    }
}
