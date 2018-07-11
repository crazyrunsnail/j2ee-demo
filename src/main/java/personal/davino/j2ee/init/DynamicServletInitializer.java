package personal.davino.j2ee.init;

import personal.davino.j2ee.servlet.dynamic.DynamicServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

@HandlesTypes(value = {DynamicServlet.class})
public class DynamicServletInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("Comming DynamicServletInitializer....");
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
        System.out.println("Init DynamicServletInitializer completed!");
    }

}
