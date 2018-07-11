package personal.davino.j2ee.bootstrap;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@ComponentScan(basePackages = "personal.davino.j2ee",
        excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootContext {
}
