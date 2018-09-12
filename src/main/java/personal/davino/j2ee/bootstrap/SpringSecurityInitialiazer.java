package personal.davino.j2ee.bootstrap;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(100)
public class SpringSecurityInitialiazer extends AbstractSecurityWebApplicationInitializer {

}
