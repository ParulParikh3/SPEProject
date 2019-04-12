package spring.event.model;

import javax.persistence.*;

@Entity
@Table(name="review")
public class Review {
	@EmbeddedId
    private UserEventEmbedded usereventid;
	

	@Column(name="review")
	private String review;
	
	@Column(name="suggestion")
	private String suggestion;
	
	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public UserEventEmbedded getUsereventid() {
		return usereventid;
	}

	public void setUsereventid(UserEventEmbedded usereventid) {
		this.usereventid = usereventid;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Review() {
		
	}
	public Review(UserEventEmbedded usereventid, String review, String suggestion ) {
		this.usereventid=usereventid;
		this.review=review;
		this.suggestion=suggestion;
	}

}
