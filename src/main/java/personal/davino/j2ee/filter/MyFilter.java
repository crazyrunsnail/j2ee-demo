package personal.davino.j2ee.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "myfilter", servletNames = "MyServlet")
public class MyFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Filter init....");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("doFilter...");
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            logger.info("filter Chain finish!");
        }
    }

    @Override
    public void destroy() {

    }
}
