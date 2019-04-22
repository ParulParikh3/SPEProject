package spring.event.repository;

import org.springframework.data.repository.CrudRepository;

import spring.event.model.Users;

public interface RegistrationRepository extends CrudRepository<Users,Integer>{
	  
	    Users findByEmail(String email);
	    Users findByUserid(long userid);
	     

}
