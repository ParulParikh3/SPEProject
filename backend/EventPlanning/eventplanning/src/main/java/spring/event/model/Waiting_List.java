package spring.event.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="waiting_list")
public class Waiting_List {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="waiting_id")
	private int waiting_id;
	
	@Column(name="userid")
	private long userid;
	
	@Column(name="eventid")
	private long eventid;
	
public Waiting_List(long userid, long eventid) {
		
		this.userid = userid;
		this.eventid = eventid;
	}

	public Waiting_List() {
	super();
}

	public int getWaiting_id() {
		return waiting_id;
	}

	@Override
public String toString() {
	return "Waiting_List [waiting_id=" + waiting_id + ", userid=" + userid + ", eventid=" + eventid + "]";
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


}
