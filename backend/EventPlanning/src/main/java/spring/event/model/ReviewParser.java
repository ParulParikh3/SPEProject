package spring.event.model;

import java.io.Serializable;

public class ReviewParser  implements Serializable {
	
	private long userid;
	private long eventid;
	private String review;
	private String suggestion;
	private static final long serialVersionUID=3L;
	
	public ReviewParser() {
		
	}
	
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getEventid() {
		return eventid;
	}
	public void setEventid(long eventid) {
		this.eventid = eventid;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	

}
