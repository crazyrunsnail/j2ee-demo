package personal.davino.j2ee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.davino.j2ee.bean.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustomization{
}
