package personal.davino.j2ee.repository.customsupport;

import org.springframework.data.repository.CrudRepository;
import personal.davino.j2ee.bean.entity.customersupport.UserPrincipal;

public interface UserRepository extends CrudRepository<UserPrincipal, Long>
{
    UserPrincipal getByUsername(String username);
}
