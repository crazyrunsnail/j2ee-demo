package personal.davino.j2ee.listener.application;

import org.springframework.context.ApplicationEvent;

public abstract class AuthenticationEvent extends ApplicationEvent{
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public AuthenticationEvent(Object source) {
        super(source);
    }
}
