package personal.davino.j2ee;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import personal.davino.j2ee.bean.entity.Author;
import personal.davino.j2ee.bean.entity.Book;
import personal.davino.j2ee.repository.AuthorRepository;
import personal.davino.j2ee.repository.BookRepository;

public class RepositoryTests extends BaseSpringTest{

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void testBook() {
        System.out.println(bookRepository);
        System.out.println(bookRepository.count());
        Page<Book> books = bookRepository.searchBooks("", new PageRequest(0, 10));
        books.getContent().stream().forEach(x -> {
            System.out.println(x.getAuthor());
        });

    }

    @Test
    public void testAuthor() {
        Page<Author> authors = authorRepository.searchAuthors("", new PageRequest(0, 10));
        authors.getContent().stream().forEach(System.out::println);
    }
}
