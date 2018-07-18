package personal.davino.j2ee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.davino.j2ee.bean.Employee;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

@Controller
public class EasyValidateController {

    @Autowired
    private MessageSource messageSource;

    @ResponseBody
    @RequestMapping("/validate")
    public String validate(@Valid Employee employee, Errors errors, HttpServletResponse response) {
        String message = messageSource.getMessage("validate.employee.firstName", new Object[]{}, Locale.CHINA);
        try {
            ResourceBundle resourceBundle = new PropertyResourceBundle(this.getClass().getClassLoader().
                    getResourceAsStream("../i18n/validation_zh_CN.properties"));
            String string = resourceBundle.getString("validate.employee.firstName");
            System.out.println(string);
        } catch (IOException e) {
            System.out.println("No file!");
        }
        System.out.println(message);
        return message;
    }

    @RequestMapping("/i18n")
    public String i18n() {
        return "i18n";
    }
}
