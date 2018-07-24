package personal.davino.j2ee.bootstrap.annotation;


import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
public @interface WebController {
    String value() default "";
}
