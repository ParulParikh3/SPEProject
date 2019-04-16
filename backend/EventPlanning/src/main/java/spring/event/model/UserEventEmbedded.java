package spring.event.model;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;
@Embeddable
public class UserEventEmbedded implements Serializable {
	
	private static final long serialVersionUID=2L;
	@Column(name="userid")
	private long userid;
	
	@Column(name="eventid")
	private long eventid;
	
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
	public UserEventEmbedded(long userid, long eventid) {
		
		this.userid = userid;
		this.eventid = eventid;
	}
	public UserEventEmbedded() {
		
	}

	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof UserEventEmbedded)) return false;
	        UserEventEmbedded that = (UserEventEmbedded) o;
	        return Objects.equals(getUserid(), that.getUserid()) &&
	                Objects.equals(getEventid(), that.getEventid());
	    }
	 
	    @Override
	    public int hashCode() {
	        return Objects.hash(getUserid(), getEventid());
	    }
}
