package personal.davino.j2ee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import personal.davino.j2ee.bean.entity.Book;

public interface BookRepositoryCustomization {
    Page<Book> searchBooks(String searchQuery, Pageable p);
}
