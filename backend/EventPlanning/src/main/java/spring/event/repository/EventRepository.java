	package spring.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import spring.event.model.Event;
import spring.event.model.EventParser;
import spring.event.model.SpeakerSponsorDTO;

public interface EventRepository extends CrudRepository<Event,Integer>{
	
	List<Event> findByPhase(String phase);
	Event findByEventname(String eventname);
	Event findByEventid(long eventid);
	
	
	@Query("select new spring.event.model.EventParser(e.eventid,e.eventname)"+" from Event as e, User_EventLink as u "+" where e.eventid=u.usereventid.eventid and u.role=?2 and u.usereventid.userid=?1 and u.status in ('approved','created') and e.phase in ('open','creation')")
	List<EventParser> findByUserid(long id,String role);
	
	@Query("select new spring.event.model.EventParser(e.eventid,e.eventname)"+" from Event as e, User_EventLink as u "+" where e.eventid=u.usereventid.eventid and u.role=?2 and u.usereventid.userid=?1 and u.status in ('approved','created') and e.phase='closed'")
	List<EventParser> findByUserid_Phase(long id,String role,String phase);
	
	@Query("select new spring.event.model.EventParser(e.eventid,e.eventname)"+" from Event as e, User_EventLink as u "+" where e.eventid=u.usereventid.eventid and u.usereventid.userid=?1 and u.status=?2")
	List<EventParser> getListByStatus(long id,String status);
	
	@Query("select new spring.event.model.EventParser(e.eventid,e.eventname) from Event as e where e.phase=?1")
	List<EventParser> findEventsByPhase(String phase);
	
	@Query("select new spring.event.model.SpeakerSponsorDTO(u.name,l.role)"+" from Users as u ,Event as e , User_EventLink as l "+" where e.eventid=l.usereventid.eventid and u.userid=l.usereventid.userid and e.organizer_id= ?1 and e.eventid=?2 and e.phase='creation'and l.status='approved'")
	List<SpeakerSponsorDTO> findEventDetails(long id,long eventid);
	
	@Query(value="select count(*) from user_eventlink where eventid=?1 and role='speaker' and status='approved'",nativeQuery=true)
	long countOfSpeaker(long id);
	
	@Query(value="select * from event where eventid=?1",nativeQuery=true)
	Event findPhaseByEventId(long id);
	
	
	@Query(value="select count(*) from user_eventlink where eventid=?1 and role='sponsor' and status='approved'",nativeQuery=true)
	long countOfSponsor(long id);
	
	@Modifying
	@Query("update Event e set e.phase='open' where e.eventid=?1")
	void UpdatePhase(long id);
}