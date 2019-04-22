package spring.event.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import spring.event.model.Waiting_List;

public interface WaitingListRepository extends CrudRepository<Waiting_List,Integer> {
	@Query(value="SELECT * FROM waiting_list w WHERE w.waiting_id=(SELECT MIN(l.waiting_id) FROM waiting_list l where l.eventid=?1)",nativeQuery=true)
	Waiting_List findByeventid(long id);
	
	@Query(value="SELECT * FROM waiting_list w where w.eventid=?2 and w.userid=?1",nativeQuery=true )
	Waiting_List findByUserandEventid(long uid,long eid);
	
	List<Waiting_List> findByEventid(long eventid);
}
