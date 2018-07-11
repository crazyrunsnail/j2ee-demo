package personal.davino.j2ee.service.impl;

import personal.davino.j2ee.service.GreetingService;

public class GreetingServiceImpl implements GreetingService{
    @Override
    public String getGreeting(String name) {
        return "hello, " + name + "! Nice to Spring world!";
    }
}
