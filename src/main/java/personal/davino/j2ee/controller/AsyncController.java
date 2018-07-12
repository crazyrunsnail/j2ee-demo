package personal.davino.j2ee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.davino.j2ee.service.NotificationService;

import java.util.Arrays;

@Controller
public class AsyncController {


    private final NotificationService notificationService;

    public AsyncController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RequestMapping("async")
    @ResponseBody
    public String async() {
        notificationService.sendNotification("subject", "message", Arrays.asList("a"));
        return "ok";
    }
}
