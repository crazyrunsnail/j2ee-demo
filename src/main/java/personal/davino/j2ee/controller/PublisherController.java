package personal.davino.j2ee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.davino.j2ee.listener.application.AuthenticationEvent;
import personal.davino.j2ee.listener.application.LoginEvent;
import personal.davino.j2ee.listener.application.LogoutEvent;
import personal.davino.j2ee.listener.application.LogoutInterestedParty;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublisherController {
    @Inject
    private ApplicationEventPublisher applicationEventPublisher;

    @Inject
    private LogoutInterestedParty logoutInterestedParty;

    @RequestMapping("/publish-login")
    @ResponseBody
    public String publishLogin(HttpServletRequest request) {
        applicationEventPublisher.publishEvent(new LoginEvent(request.getRemoteAddr()));
        return "OK";
    }

    @RequestMapping("/publish-logout")
    @ResponseBody
    public String publishLogout(HttpServletRequest request) {
        applicationEventPublisher.publishEvent(new LogoutEvent(request.getRemoteAddr()));
        return "OK";
    }
}
