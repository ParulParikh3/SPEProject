package spring.event.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="login")
public class Login {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY
	@Column(name="userid")
	private long userid;
	
 @Column(name = "username")
	private String username;
 
	@Column(name = "password")
	private String pass;
	
	
	
    public Login() {
		
	}
	
    public long getUserid() {
		return userid;
	}




	public void setUserid(long userid) {
		this.userid = userid;
	}




	

	

	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	
 
	public Login( String username, String pass) {
		
		
		this.username = username;
		this.pass = pass;
	}

	
}
