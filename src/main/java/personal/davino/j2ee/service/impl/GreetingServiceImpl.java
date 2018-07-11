package personal.davino.j2ee.service.impl;

import org.springframework.stereotype.Service;
import personal.davino.j2ee.service.GreetingService;

@Service
public class GreetingServiceImpl implements GreetingService{
    @Override
    public String getGreeting(String name) {
        return "hello, " + name + "! Nice to Spring world!";
    }
}
