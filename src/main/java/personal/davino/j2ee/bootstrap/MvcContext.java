package personal.davino.j2ee.bootstrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import personal.davino.j2ee.controller.HelloController;
import personal.davino.j2ee.service.GreetingService;
import personal.davino.j2ee.service.impl.GreetingServiceImpl;


@ComponentScan(basePackages = {"personal.davino.j2ee"},
        useDefaultFilters = false, includeFilters = {@ComponentScan.Filter(Controller.class)})
public class MvcContext {


}
