package personal.davino.j2ee.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import personal.davino.j2ee.listener.session.SessionRegistry;

import javax.inject.Inject;
import java.util.Map;

@Controller
@RequestMapping("session")
public class SessionListController {
    @Inject
    SessionRegistry sessionRegistry;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Map<String, Object> model)
    {
        model.put("timestamp", System.currentTimeMillis());
        model.put("numberOfSessions", this.sessionRegistry.getNumberOfSessions());
        model.put("sessionList", this.sessionRegistry.getAllSessions());

        return "session/list";
    }
}