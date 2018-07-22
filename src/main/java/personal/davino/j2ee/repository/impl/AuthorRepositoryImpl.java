package personal.davino.j2ee.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import personal.davino.j2ee.bean.entity.Author;
import personal.davino.j2ee.bean.entity.Book;
import personal.davino.j2ee.repository.AuthorRepository;
import personal.davino.j2ee.repository.AuthorRepositoryCustomization;
import personal.davino.j2ee.repository.GenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;

@Repository
@Transactional
public class AuthorRepositoryImpl implements AuthorRepositoryCustomization{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Author> searchAuthors(String searchQuery, Pageable p) {
        Query query = entityManager.createQuery("SELECT a FROM Author a");
        Query countQuery = entityManager.createQuery("SELECT count(a) FROM Author a", Long.class);
        Long count = (Long)countQuery.getSingleResult();
        query.setFirstResult(p.getOffset());
        query.setMaxResults(p.getPageSize());
        return new PageImpl(query.getResultList(), p, count);
    }

}
