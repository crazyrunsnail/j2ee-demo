package personal.davino.j2ee.repository.customsupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import personal.davino.j2ee.bean.entity.customersupport.TicketComment;

public interface TicketCommentRepository
        extends CrudRepository<TicketComment, Long>
{
    Page<TicketComment> getByTicketId(long ticketId, Pageable p);
}
