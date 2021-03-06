package personal.davino.j2ee.controller.customersupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import personal.davino.j2ee.bean.entity.customersupport.UserPrincipal;
import personal.davino.j2ee.bootstrap.annotation.WebController;
import personal.davino.j2ee.service.customersupport.AuthenticationService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.Map;

@WebController
public class AuthenticationController {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Inject
    AuthenticationService authenticationService;

    @RequestMapping("logout")
    public View logout(HttpServletRequest request, HttpSession session) {
        if (log.isDebugEnabled() && request.getUserPrincipal() != null)
            log.debug("User {} logged out.", request.getUserPrincipal().getName());
        session.invalidate();

        return new RedirectView("/login", true, false);
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login(Map<String, Object> model, HttpSession session) {
        if (UserPrincipal.getPrincipal(session) != null)
            return this.getTicketRedirect();

        model.put("loginFailed", false);
        model.put("loginForm", new LoginForm());

        return new ModelAndView("login");
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(Map<String, Object> model, HttpSession session,
                              HttpServletRequest request, @Valid LoginForm form,
                              Errors errors) {
        if (UserPrincipal.getPrincipal(session) != null)
            return this.getTicketRedirect();

        if (errors.hasErrors()) {
            form.setPassword(null);
            return new ModelAndView("login");
        }

        Principal principal;
        try {
            principal = this.authenticationService.authenticate(
                    form.getUsername(), form.getPassword()
            );
        } catch (ConstraintViolationException e) {
            form.setPassword(null);
            model.put("validationErrors", e.getConstraintViolations());
            return new ModelAndView("login");
        }

        if (principal == null) {
            form.setPassword(null);
            model.put("loginFailed", true);
            model.put("loginForm", form);
            return new ModelAndView("login");
        }

        UserPrincipal.setPrincipal(session, principal);
        request.changeSessionId();
        return this.getTicketRedirect();
    }

    private ModelAndView getTicketRedirect() {
        return new ModelAndView(new RedirectView("/ticket/list", true, false));
    }

    public static class LoginForm {
        @NotBlank(message = "{validate.authenticate.username}")
        private String username;
        @NotBlank(message = "{validate.authenticate.password}")
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
