package personal.davino.j2ee.service.customersupport;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.davino.j2ee.bean.entity.customersupport.UserPrincipal;
import personal.davino.j2ee.repository.customsupport.UserRepository;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class DefaultAuthenticationService implements AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(DefaultAuthenticationService.class);
    private static final SecureRandom RANDOM;
    private static final int HASHING_ROUNDS = 10;

    static {
        try {
            RANDOM = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    @Inject
    UserRepository userRepository;

    @Override
    @Transactional
    public UserPrincipal authenticate(String username, String password) {
        UserPrincipal principal = this.userRepository.getByUsername(username);
        if (principal == null) {
            log.warn("Authentication failed for non-existent user {}.", username);
            return null;
        }

        if (!BCrypt.checkpw(
                password,
                new String(principal.getPassword(), StandardCharsets.UTF_8)
        )) {
            log.warn("Authentication failed for user {}.", username);
            return null;
        }

        log.debug("User {} successfully authenticated.", username);

        return principal;
    }

    @Override
    @Transactional
    public void saveUser(UserPrincipal principal, String newPassword) {
        if (newPassword != null && newPassword.length() > 0) {
            String salt = BCrypt.gensalt(HASHING_ROUNDS, RANDOM);
            principal.setPassword(BCrypt.hashpw(newPassword, salt).getBytes());
        }

        this.userRepository.save(principal);
    }
}
