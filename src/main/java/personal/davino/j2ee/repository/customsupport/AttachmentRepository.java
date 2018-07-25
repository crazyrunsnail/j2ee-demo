package personal.davino.j2ee.repository.customsupport;

import org.springframework.data.repository.CrudRepository;
import personal.davino.j2ee.bean.entity.customersupport.Attachment;

public interface AttachmentRepository extends CrudRepository<Attachment, Long> {
}
