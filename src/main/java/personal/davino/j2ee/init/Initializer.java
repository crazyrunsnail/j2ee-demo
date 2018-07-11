package personal.davino.j2ee.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import javax.servlet.http.HttpServlet;
import java.util.Set;

@HandlesTypes(value = {HttpServlet.class, Filter.class})
public class Initializer implements ServletContainerInitializer {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        logger.info("HttpServlet & Filter Initializer onStart()...");
        for (Class c: set) {
            System.out.println(c.getCanonicalName());
        }
        logger.info("HttpServlet & Filter Initializer init completed!");
    }

}
