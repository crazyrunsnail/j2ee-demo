package personal.davino.j2ee.init;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import javax.servlet.http.HttpServlet;
import java.util.Set;

@HandlesTypes(value = {HttpServlet.class, Filter.class})
public class Initializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        for (Class c: set) {
            System.out.println(c.getCanonicalName());
        }
    }

}
