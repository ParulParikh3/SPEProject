package spring.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import spring.event.model.OptionalDetails;
import spring.event.model.RoleDetailDTO;
import spring.event.model.Users;

public interface OptionalDetailsRepository extends CrudRepository<OptionalDetails,Long> {

	@Query("select new spring.event.model.RoleDetailDTO(u.userid,u.name,u.email,o.educational_details,o.linkedin,o.resume)"+" from Users as u , OptionalDetails as o  where u.userid=o.userid and o.role='speaker'  ")
	List<RoleDetailDTO> findSpeakerDetails();
	
	@Query("select new spring.event.model.RoleDetailDTO(u.userid,u.name,u.email,o.educational_details,o.linkedin,o.resume)"+" from Users as u , OptionalDetails as o  where u.userid=o.userid and o.role='sponsor' ")
	List<RoleDetailDTO> findSponsorDetails();
}

