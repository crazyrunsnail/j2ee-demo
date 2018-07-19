package personal.davino.j2ee.servlet;

import personal.davino.j2ee.bean.entity.Author;
import personal.davino.j2ee.bean.entity.Book;
import personal.davino.j2ee.bean.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@WebServlet(name = "entityServlet", urlPatterns = "/entities", loadOnStartup = 1)
public class EntityServlet extends HttpServlet{

    private final Random random;
    private EntityManagerFactory factory;

    public EntityServlet() {
        try {
            this.random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager manager = null;
        EntityTransaction transaction = null;
        try {
            manager = this.factory.createEntityManager();
            transaction = manager.getTransaction();
            transaction.begin();
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Publisher> q1 = builder.createQuery(Publisher.class); request.setAttribute("publishers", manager.createQuery(
                    q1.select(q1.from(Publisher.class)) ).getResultList());
            CriteriaQuery<Author> q2 = builder.createQuery(Author.class); request.setAttribute("authors", manager.createQuery(
                    q2.select(q2.from(Author.class)) ).getResultList());
            CriteriaQuery<Book> q3 = builder.createQuery(Book.class); request.setAttribute("books", manager.createQuery(
                    q3.select(q3.from(Book.class)) ).getResultList());
            transaction.commit();
            request.setAttribute("a", "aaaa");
            request.getRequestDispatcher("/WEB-INF/view/entities.jsp").forward(request, response);
        }
        catch(Exception e) {
            if(transaction != null && transaction.isActive()) transaction.rollback();
            e.printStackTrace(response.getWriter()); }
        finally {
            if(manager != null && manager.isOpen()) manager.close();
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        this.factory = Persistence.createEntityManagerFactory("EntityMappings");
    }

    @Override
    public void destroy() {
        super.destroy();
        this.factory.close();
    }
}
