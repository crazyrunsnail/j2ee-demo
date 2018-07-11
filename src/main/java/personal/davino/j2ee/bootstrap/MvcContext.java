package personal.davino.j2ee.bootstrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import personal.davino.j2ee.controller.HelloController;
import personal.davino.j2ee.service.GreetingService;
import personal.davino.j2ee.service.impl.GreetingServiceImpl;


@ComponentScan(basePackages = {"personal.davino.j2ee"},
        useDefaultFilters = false, includeFilters = {@ComponentScan.Filter(Controller.class)})
public class MvcContext {

    /**
     * 配置JSP view Resolver
     * Prefix 结尾需要 {@code /}
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * 当 Controller 返回 void时, 由translator来处理, 根据url path来取
     * 这里的路径会加上 viewResovler 设置的 prefix 和 suffix
     * @return
     */
    @Bean
    public RequestToViewNameTranslator viewNameTranslator() {
        DefaultRequestToViewNameTranslator translator = new DefaultRequestToViewNameTranslator();
        translator.setPrefix("default/");
        return translator;
    }


}
