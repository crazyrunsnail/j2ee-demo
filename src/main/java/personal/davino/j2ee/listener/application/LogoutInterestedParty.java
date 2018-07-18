package personal.davino.j2ee.listener.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class LogoutInterestedParty implements ApplicationListener<LogoutEvent>{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void onApplicationEvent(LogoutEvent event) {
        logger.info("Logout event for IP address {}.", event.getSource());
    }
}
