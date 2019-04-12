package spring.event.repository;



import org.springframework.data.repository.CrudRepository;

import spring.event.model.Login;

public interface LoginRepository extends CrudRepository<Login,Long> {

	
	Login findByUsername(String name);

}