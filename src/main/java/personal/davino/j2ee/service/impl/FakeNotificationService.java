package personal.davino.j2ee.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import personal.davino.j2ee.service.NotificationService;

import java.util.Collection;

@Service
public class FakeNotificationService implements NotificationService {

    private static final Logger log = LoggerFactory.getLogger(FakeNotificationService.class);

    @Override
    @Async
    public void sendNotification(String subject, String message,
                                 Collection<String> recipients) {
        log.info("Started notifying recipients {}.", recipients);
        try {
            Thread.sleep(5_000L);
        } catch (InterruptedException ignore) {
        }
        log.info("Finished notifying recipients.");
    }
}
