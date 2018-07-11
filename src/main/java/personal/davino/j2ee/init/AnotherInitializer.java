package personal.davino.j2ee.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import javax.servlet.http.HttpServlet;
import java.util.Set;

@HandlesTypes(value = {ServletContainerInitializer.class})
public class AnotherInitializer implements ServletContainerInitializer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        logger.debug("ServletContainerInitializer AnotherInitializer onStart()...");
        for (Class c: set) {
            System.out.println(c.getCanonicalName());
        }
        logger.debug("ServletContainerInitializer init completed!");
    }

}
