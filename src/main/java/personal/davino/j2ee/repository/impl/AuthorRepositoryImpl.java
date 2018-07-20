package personal.davino.j2ee.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import personal.davino.j2ee.bean.entity.Author;
import personal.davino.j2ee.repository.AuthorRepository;
import personal.davino.j2ee.repository.GenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

@Repository
@Transactional
public class AuthorRepositoryImpl extends GenericJpaRepository<Long, Author> implements AuthorRepository{

}
