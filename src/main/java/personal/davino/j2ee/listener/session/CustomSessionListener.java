package personal.davino.j2ee.listener.session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CustomSessionListener implements HttpSessionListener{
    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
