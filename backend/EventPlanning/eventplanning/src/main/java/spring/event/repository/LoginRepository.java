package spring.event.repository;



import org.springframework.data.repository.CrudRepository;

import spring.event.model.Login;

public interface LoginRepository extends CrudRepository<Login,Integer> {

	
	Login findByUsername(String name);

}
