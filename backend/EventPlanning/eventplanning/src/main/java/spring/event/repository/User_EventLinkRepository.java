package spring.event.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import spring.event.model.UserEventEmbedded;
import spring.event.model.User_EventLink;

@Transactional
public interface User_EventLinkRepository extends CrudRepository<User_EventLink,UserEventEmbedded>{
	User_EventLink findByUsereventid(UserEventEmbedded usereventid);
	
	@Query(value="select event.eventname from event inner join user_eventlink on user_eventlink.eventid=event.eventid where user_eventlink.status='requested' and user_eventlink.userid=?1",nativeQuery=true)
	List<String> findByStatusRequest(long id);
	
	@Modifying
	@Query(value="update user_eventlink set status=?2 where userid=?1",nativeQuery=true)
	void changeStatus(long id,String status);
	
	@Query(value="select users.name,users.userid,optionaldetails.educational_details from users inner join user_eventlink on user_eventlink.userid=users.userid inner join optionaldetails ON optionaldetails.userid=users.userid  where user_eventlink.role='speaker' and user_eventlink.status='applied' and user_eventlink.eventid=(select eventid from user_eventlink where userid=?1 and role='organizer')" , nativeQuery=true)
	List<Object[]> requestsFromSpeaker(long id);

	@Query(value="select users.name,users.userid,optionaldetails.educational_details from users inner join user_eventlink on user_eventlink.userid=users.userid inner join optionaldetails ON optionaldetails.userid=users.userid  where user_eventlink.role='sponsor' and user_eventlink.status='applied' and user_eventlink.eventid=(select eventid from user_eventlink where userid=?1 and role='organizer')" , nativeQuery=true)
	List<Object[]> requestsFromSponsor(long id);
	
	@Query(value="select users.name,users.userid from users where users.userid=(select user_eventlink.userid from user_eventlink where status='applied' and role='organizer' and eventid=(select eventid from event where organizer_id=?1))",nativeQuery=true)
	List<Object> findByOrganizerRequest(long id);

}