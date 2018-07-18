package personal.davino.j2ee.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SchedulingServiceImpl {
    private final static Logger logger = LoggerFactory.getLogger(SchedulingServiceImpl.class);

//    @Scheduled(fixedRate = 1_000L, initialDelay = 4000L)
    public void schedule() {
        logger.info("schedule begin...");
        try {
            TimeUnit.SECONDS.sleep(10L);
        } catch (InterruptedException ingore) {
            logger.error("线程被打断.");
        }
        logger.info("schedule end.");
    }
}
