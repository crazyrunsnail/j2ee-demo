package personal.davino.j2ee.init;

import javax.servlet.Filter;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import javax.servlet.http.HttpServlet;
import java.util.Set;

@HandlesTypes(value = {ServletContainerInitializer.class})
public class AnotherInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("Coming AnotherInitializer...");
        for (Class c: set) {
            System.out.println(c.getCanonicalName());
        }
        System.out.println("AnotherInitializer init completed!");
    }

}
