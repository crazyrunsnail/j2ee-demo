package personal.davino.j2ee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import personal.davino.j2ee.repository.BookRepository;
import personal.davino.j2ee.repository.impl.BookRepositoryImpl;
import personal.davino.j2ee.service.BookManager;

import javax.inject.Inject;
import java.util.Map;

@Controller
public class BookController {

    @Inject
    private BookManager bookManager;

    @Inject
    private BookRepositoryImpl bookRepository;

    @RequestMapping(value = "/entities", method = RequestMethod.GET)
    public String list(Map<String, Object> model) {
        model.put("publishers", this.bookManager.getPublishers());
        model.put("authors", this.bookManager.getAuthors());
        model.put("books", this.bookManager.getBooks());
        return "entities";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String customBooks(Map<String, Object> model) {
        return "entities";
    }
}
