package personal.davino.j2ee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import personal.davino.j2ee.bean.entity.Author;

public interface AuthorRepositoryCustomization {
    Page<Author> searchAuthors(String searchQuery, Pageable p);
}
