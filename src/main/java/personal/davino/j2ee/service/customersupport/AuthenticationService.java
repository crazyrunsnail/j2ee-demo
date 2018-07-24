package personal.davino.j2ee.service.customersupport;

import org.springframework.validation.annotation.Validated;
import personal.davino.j2ee.bean.entity.customersupport.UserPrincipal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
public interface AuthenticationService {
    UserPrincipal authenticate(
            @NotBlank(message = "{validate.authenticate.username}")
                    String username,
            @NotBlank(message = "{validate.authenticate.password}")
                    String password
    );

    void saveUser(
            @NotNull(message = "{validate.authenticate.saveUser}") @Valid
                    UserPrincipal principal,
            String newPassword
    );
}
