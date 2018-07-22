package personal.davino.j2ee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.davino.j2ee.bean.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>, AuthorRepositoryCustomization{

}
