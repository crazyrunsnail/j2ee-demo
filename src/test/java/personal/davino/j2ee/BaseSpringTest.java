package personal.davino.j2ee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import personal.davino.j2ee.bean.entity.Author;
import personal.davino.j2ee.bean.entity.Book;
import personal.davino.j2ee.bootstrap.RootContext;
import personal.davino.j2ee.repository.AuthorRepository;
import personal.davino.j2ee.repository.BookRepository;
import personal.davino.j2ee.repository.impl.BookRepositoryImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootContext.class})
public class BaseSpringTest {

}
