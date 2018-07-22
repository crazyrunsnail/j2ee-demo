package personal.davino.j2ee.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import personal.davino.j2ee.bean.entity.Book;
import personal.davino.j2ee.repository.BookRepository;
import personal.davino.j2ee.repository.BookRepositoryCustomization;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepositoryCustomization {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Book> searchBooks(String searchQuery, Pageable p) {
        Query query = entityManager.createQuery("SELECT a FROM Book a");
        Query countQuery = entityManager.createQuery("SELECT count(a) FROM Book a", Long.class);
        Long count = (Long)countQuery.getSingleResult();
        query.setFirstResult(p.getOffset());
        query.setMaxResults(p.getPageSize());
        return new PageImpl(query.getResultList(), p, count);
    }

}
