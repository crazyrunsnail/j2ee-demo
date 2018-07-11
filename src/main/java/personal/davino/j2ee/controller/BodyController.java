package personal.davino.j2ee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.davino.j2ee.bean.User;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BodyController {

    @RequestMapping(value = "/user")
    @ResponseBody
    public User user() {
        User user = new User();
        user.setAge(2);
        user.setName("Jeff");
        return user;
    }
}
