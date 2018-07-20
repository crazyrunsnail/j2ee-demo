package personal.davino.j2ee.service;

import personal.davino.j2ee.bean.entity.Author;
import personal.davino.j2ee.bean.entity.Book;
import personal.davino.j2ee.bean.entity.Publisher;

import java.util.List;

public interface BookManager {
    List<Author> getAuthors();

    List<Book> getBooks();

    List<Publisher> getPublishers();

    void saveAuthor(Author author);

    void saveBook(Book book);

    void savePublisher(Publisher publisher);

}
