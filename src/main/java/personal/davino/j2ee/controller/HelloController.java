package personal.davino.j2ee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.davino.j2ee.service.GreetingService;

@Controller
public class HelloController {

    private final GreetingService greetingService;

    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @RequestMapping("/greeting")
    @ResponseBody
    public String helloWorld() {
        return "hello world!";
    }

    @RequestMapping("/greeting/{name}")
    @ResponseBody
    public String helloName(@PathVariable String name) {
        return greetingService.getGreeting(name);
    }
}
