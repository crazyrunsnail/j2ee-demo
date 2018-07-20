package personal.davino.j2ee.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import personal.davino.j2ee.bean.entity.Book;
import personal.davino.j2ee.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class BookRepositoryImpl extends GenericJpaRepository<Long, Book> implements BookRepository {

}
