package personal.davino.j2ee.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

public class Bootstrap implements WebApplicationInitializer{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.debug("Init Spring AnnotationConfigWebApplicationContext");

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootContext.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MvcContext.class);

        ServletRegistration.Dynamic springDispather =
                servletContext.addServlet("springDispather", new DispatcherServlet(context));
        springDispather.setLoadOnStartup(1);
        springDispather.addMapping("/");
        // NOT USE!!! 使用星号会.jsp的文件也交给springDispatcher来解析
        //springDispather.addMapping("/*");

        // 加静态资源的方法
        servletContext.getServletRegistration("default")
                .addMapping("/static/*", "*.html");
        logger.debug("Spring AnnotationConfigWebApplicationContext completed!");
    }
}
