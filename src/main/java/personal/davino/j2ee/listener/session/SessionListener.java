package personal.davino.j2ee.listener.session;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;

public class SessionListener implements HttpSessionListener, HttpSessionIdListener,
        ServletContextListener{

    @Inject SessionRegistry sessionRegistry;

    @Override
    public void sessionIdChanged(HttpSessionEvent event, String oldSessionId) {
        sessionRegistry.updateSessionId(event.getSession(), oldSessionId);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        sessionRegistry.addSession(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        sessionRegistry.removeSession(se.getSession());
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext applicationContext =
                WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        beanFactory.autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
        beanFactory.initializeBean(this, "sessionListener");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
