package personal.davino.j2ee.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import personal.davino.j2ee.servlet.dynamic.DynamicServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

@HandlesTypes(value = {DynamicServlet.class})
public class DynamicServletInitializer implements ServletContainerInitializer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        logger.debug("DynamicServletInitializer onStart()....");
        for (Class c : set) {
            if (DynamicServlet.class.isAssignableFrom(c)) {
                String servletName = c.getSimpleName();
                String urlPattern = c.getSimpleName();
                ServletRegistration.Dynamic servlet = servletContext.addServlet(servletName,
                        c);
                servlet.addMapping("/" + urlPattern);
                System.out.printf("[%s] servlet match url pattern: [/%20s] \n", servletName, urlPattern);
            }
        }
        logger.debug("Init DynamicServletInitializer completed!");
    }

}
