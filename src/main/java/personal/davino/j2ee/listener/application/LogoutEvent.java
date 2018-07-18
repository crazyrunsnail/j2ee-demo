package personal.davino.j2ee.listener.application;

public class LogoutEvent extends AuthenticationEvent{
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public LogoutEvent(String username) {
        super(username);
    }
}
