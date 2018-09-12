package personal.davino.j2ee.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import personal.davino.j2ee.filter.AuthenticationFilter;
import personal.davino.j2ee.filter.MyFilter;
import personal.davino.j2ee.listener.session.SessionListener;

import javax.servlet.*;
import java.util.EnumSet;

@Order(0)
public class Bootstrap implements WebApplicationInitializer{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.debug("Init Spring AnnotationConfigWebApplicationContext");

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootContextConfiguration.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));
        servletContext.addListener(SessionListener.class);

        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(WebServletContextConfiguration.class);

        ServletRegistration.Dynamic dispatcher =
                servletContext.addServlet("springDispather", new DispatcherServlet(webContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.setMultipartConfig(new MultipartConfigElement(null, 20_971_520L, 41_943_040L, 512_000));
        dispatcher.addMapping("/");
        // NOT USE!!! 使用星号会.jsp的文件也交给springDispatcher来解析
        //dispatcher.addMapping("/*");


        AnnotationConfigWebApplicationContext restContext =
                new AnnotationConfigWebApplicationContext();
        restContext.register(RestServletContextConfiguration.class);
        DispatcherServlet restServlet = new DispatcherServlet(restContext);
        restServlet.setDispatchOptionsRequest(true);
        dispatcher = servletContext.addServlet("springRestDispatcher", restServlet);
        dispatcher.setLoadOnStartup(2);
        dispatcher.addMapping("/rest/*");

        // 加静态资源的方法
        servletContext.getServletRegistration("default")
                .addMapping("/static/*");

        servletContext.addFilter("authFilter", new AuthenticationFilter())
                .addMappingForUrlPatterns(null, false, "/ticket", "/ticket/*", "/chat", "/chat/*",
                "/session", "/session/*");

//        servletContext.addFilter("myFilter", new MyFilter()).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST,
//                DispatcherType.ASYNC),
//                false, "/*");
        servletContext.addFilter("myFilter", new MyFilter()).addMappingForUrlPatterns(null,
                false, "/*");

        logger.debug("Spring AnnotationConfigWebApplicationContext completed!");
    }
}
