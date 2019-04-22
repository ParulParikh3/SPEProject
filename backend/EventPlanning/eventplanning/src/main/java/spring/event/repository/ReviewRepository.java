package spring.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import spring.event.model.Review;
import spring.event.model.Review_SuggestionParser;
import spring.event.model.UserEventEmbedded;

public interface ReviewRepository extends CrudRepository<Review,Long> {

	Review findReviewByUsereventid(UserEventEmbedded usereventid);
	
	@Query("select new spring.event.model.Review_SuggestionParser(l.username,r.review)"+" from Login as l, Review as r "+" where l.userid=r.usereventid.userid and r.usereventid.eventid=?1 and r.review !=''")
	List<Review_SuggestionParser> getReviewsByUsereventid(long eventid);
	
	@Query("select new spring.event.model.Review_SuggestionParser(l.username,r.suggestion)"+" from Login as l, Review as r "+" where l.userid=r.usereventid.userid and r.usereventid.eventid=?1 and r.suggestion !=''")
	List<Review_SuggestionParser> getSuggestionsByUsereventid(long eventid);

}
