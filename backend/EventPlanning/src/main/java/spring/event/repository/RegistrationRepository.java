package spring.event.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import spring.event.model.Login;
import spring.event.model.Users;

public interface RegistrationRepository extends CrudRepository<Users,Long> {
	Users findByEmail(String email);
    Users findByUserid(long userid);

}
