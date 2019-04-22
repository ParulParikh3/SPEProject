package spring.event.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import spring.event.model.OptionalDetails;
import spring.event.model.RoleDetailDTO;

@Transactional
public interface OptionalDetailsRepository extends CrudRepository<OptionalDetails,Long> {

	OptionalDetails findByUserid(long userid);
	@Modifying
	@Query(value="update optionaldetails set resume=?1,linkedin=?2,educational_details=?3 where userid=?4",nativeQuery=true)
	void updateOptionaldetails(String resume,String linkedin, String educational_details,long userid);
	
	OptionalDetails findById(long userid);
	
	@Query("select new spring.event.model.RoleDetailDTO(u.userid,u.name,u.email,o.educational_details,o.linkedin,o.resume)"+" from Users as u , OptionalDetails as o where u.userid=o.userid and o.role='speaker' and o.userid not in(select w.usereventid.userid from User_EventLink as w where w.usereventid.eventid=?1 and w.role in ('speaker','organizer') and w.status in ('approved','applied','created'))  ")
	List<RoleDetailDTO> findSpeakerDetails(long eventid);
	
	@Query("select new spring.event.model.RoleDetailDTO(u.userid,u.name,u.email,o.educational_details,o.linkedin,o.resume)"+" from Users as u , OptionalDetails as o  where u.userid=o.userid and o.role='sponsor' and o.userid not in(select w.usereventid.userid from User_EventLink as w where w.usereventid.eventid=?1 and w.role='sponsor' and w.status in ('approved','applied'))")
	List<RoleDetailDTO> findSponsorDetails(long eventid);
}

