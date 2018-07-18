package personal.davino.j2ee.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;

/**
 * 记得加入 @Configuration, 不然 {@code taskScheduler} 不能注入
 */
@ComponentScan(basePackages = "personal.davino.j2ee",
        excludeFilters = {@ComponentScan.Filter(Controller.class)})
@EnableAsync(proxyTargetClass = true)
@EnableScheduling
@Configuration
public class RootContext implements AsyncConfigurer, SchedulingConfigurer {

    private final static Logger LOGGER = LoggerFactory.getLogger(RootContext.class);

    private final static Logger schedulingLogger = LoggerFactory.getLogger(LOGGER.getClass() + ".[scheduling]");

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        LOGGER.info("Setting up thread pool task scheduler with 20 threads.");
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(20);
        scheduler.setThreadNamePrefix("task-");
        scheduler.setAwaitTerminationSeconds(60);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        scheduler.setErrorHandler(t -> schedulingLogger.error(
                "Unknown error occurred while executing task.", t
        ));
        scheduler.setRejectedExecutionHandler(
                (r, e) -> schedulingLogger.error(
                        "Execution of task {} was rejected for unknown reasons.", r
                ));
        return scheduler;
    }

    @Override
    public Executor getAsyncExecutor() {
        Executor executor = this.taskScheduler();
        LOGGER.info("Configuring asynchronous method executor {}.", executor);
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        TaskScheduler scheduler = this.taskScheduler();
        LOGGER.info("Configuring scheduled method executor {}.", scheduler);
        registrar.setTaskScheduler(scheduler);
    }

    /**
     * 配置 MessageSource
     * 在validate可能会用到
     * @return
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasenames("classpath:i18n/titles", "classpath:i18n/messages", "classpath:i18n/errors",
                "classpath:i18n/validation");
        return messageSource;
    }

    /**
     * 注册validator
     * @return
     */
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() throws ClassNotFoundException {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        validator.setProviderClass(Class.forName("org.hibernate.validator.HibernateValidator"));
        return validator;
    }

    /**
     * 注册方法校验
     * @return
     * @throws ClassNotFoundException
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() throws ClassNotFoundException {
        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        methodValidationPostProcessor.setValidator(localValidatorFactoryBean());
        return methodValidationPostProcessor;
    }
}
