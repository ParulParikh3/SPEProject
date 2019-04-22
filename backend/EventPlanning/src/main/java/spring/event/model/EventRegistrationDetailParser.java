package spring.event.model;

public class EventRegistrationDetailParser {
	private long userid;
	private long eventid;
	private String role;
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public void setEventid(long eventid) {
		this.eventid = eventid;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	private int fees;
	
	public EventRegistrationDetailParser(long userid, long eventid, String role, int fees) {
		super();
		this.userid = userid;
		this.eventid = eventid;
		this.role = role;
		this.fees = fees;
	}
	public long getUserid() {
		return userid;
	}
	public long getEventid() {
		return eventid;
	}
	public String getRole() {
		return role;
	}
	public int getFees() {
		return fees;
	}

}
