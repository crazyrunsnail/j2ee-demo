package personal.davino.j2ee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.davino.j2ee.bean.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long>{
}
