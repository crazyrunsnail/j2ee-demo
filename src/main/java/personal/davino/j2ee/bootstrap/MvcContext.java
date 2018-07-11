package personal.davino.j2ee.bootstrap;

import org.springframework.context.annotation.Bean;
import personal.davino.j2ee.controller.HelloController;
import personal.davino.j2ee.service.GreetingService;
import personal.davino.j2ee.service.impl.GreetingServiceImpl;

public class MvcContext {

    @Bean
    public GreetingService getGreetingServie() {
        return new GreetingServiceImpl();
    }

    @Bean
    public HelloController getHelloController() {
        return new HelloController(getGreetingServie());
    }
}
