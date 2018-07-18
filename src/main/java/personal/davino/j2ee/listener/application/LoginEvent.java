package personal.davino.j2ee.listener.application;

public class LoginEvent extends AuthenticationEvent{
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public LoginEvent(String username) {
        super(username);
    }
}
