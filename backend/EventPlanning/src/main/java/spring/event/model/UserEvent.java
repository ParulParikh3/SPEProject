package spring.event.model;
import java.io.Serializable;

public class UserEvent implements Serializable  {
	
	private long userid;
	private long eventid;
	private String role;
	private String linkedin;
	private String educational_details;
	
	
	private static final long serialVersionUID=1L;
	public long getUserid() {
		return userid;
	}
	
	public String getEducational_details() {
		return educational_details;
	}
	public void setEducational_details(String educational_details) {
		this.educational_details = educational_details;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

}
