package personal.davino.j2ee.listener.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.EnumSet;

@WebListener
public class CustomerServletContextListener implements ServletContextListener{
    private static final Logger logger = LoggerFactory.getLogger(CustomerServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.debug("CustomerServletContextListener....");
        ServletContext servletContext = sce.getServletContext();
        FilterRegistration.Dynamic filter = servletContext.addFilter("anotherFilter", new AnotherMyFilter());
        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.ASYNC, DispatcherType.REQUEST) ,
                false, "/");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }


    public static class AnotherMyFilter implements Filter {


        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            logger.debug("Another Myfilter init...");
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        }

        @Override
        public void destroy() {

        }
    }
}
