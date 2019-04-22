package spring.event.model;

import javax.persistence.*;

@Entity
@Table(name="user_eventlink")
public class User_EventLink {
	@EmbeddedId
    private UserEventEmbedded usereventid;
	
	@Column(name="role")
	private String role;
	
	@Column(name="amountpaid")
	private int amountpaid;
	
	@Column(name="status")
	private String status;

	

	public User_EventLink(UserEventEmbedded usereventid, String role, int amountpaid, String status) {
		
		this.usereventid = usereventid;
		this.role = role;
		this.amountpaid = amountpaid;
		this.status = status;
	}

	public UserEventEmbedded getUsereventid() {
		return usereventid;
	}

	public void setUsereventid(UserEventEmbedded usereventid) {
		this.usereventid = usereventid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getAmountpaid() {
		return amountpaid;
	}

	public void setAmountpaid(int amountpaid) {
		this.amountpaid = amountpaid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User_EventLink() {
		
	}

	@Override
	public String toString() {
		return "User_EventLink [usereventid=" + usereventid + ", role=" + role + ", amountpaid=" + amountpaid
				+ ", status=" + status + "]";
	}

	
}