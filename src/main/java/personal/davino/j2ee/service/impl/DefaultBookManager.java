package personal.davino.j2ee.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.davino.j2ee.bean.entity.Author;
import personal.davino.j2ee.bean.entity.Book;
import personal.davino.j2ee.bean.entity.Publisher;
import personal.davino.j2ee.repository.AuthorRepository;
import personal.davino.j2ee.repository.BookRepository;
import personal.davino.j2ee.repository.PublisherRepository;
import personal.davino.j2ee.service.BookManager;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultBookManager implements BookManager{
    @Inject
    AuthorRepository authorRepository;
    @Inject
    BookRepository bookRepository;
    @Inject
    PublisherRepository publisherRepository;

    @Override
    @Transactional
    public List<Author> getAuthors()
    {
        return this.toList(this.authorRepository.findAll());
    }

    @Override
    @Transactional
    public List<Book> getBooks()
    {
        return this.toList(this.bookRepository.findAll());
    }

    @Override
    @Transactional
    public List<Publisher> getPublishers()
    {
        return this.toList(this.publisherRepository.findAll());
    }

    private <E> List<E> toList(Iterable<E> i)
    {
        List<E> list = new ArrayList<>();
        i.forEach(list::add);
        return list;
    }

    @Override
    @Transactional
    public void saveAuthor(Author author)
    {
        if(author.getId() < 1)
            this.authorRepository.save(author);
        else
            this.authorRepository.save(author);
    }

    @Override
    @Transactional
    public void saveBook(Book book)
    {
        if(book.getId() < 1)
            this.bookRepository.save(book);
        else
            this.bookRepository.save(book);
    }


    @Override
    @Transactional
    public void savePublisher(Publisher publisher)
    {
        if(publisher.getId() < 1)
            this.publisherRepository.save(publisher);
        else
            this.publisherRepository.save(publisher);
    }

}
