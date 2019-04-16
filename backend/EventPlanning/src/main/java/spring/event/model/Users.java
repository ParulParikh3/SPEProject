package spring.event.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid")
	private long userid;
 
	

	

	public long getUserid() {
		return userid;
	}

	

	@Override
	public String toString() {
		return "Users [userid=" + userid + ", name=" + name + ", phone_no=" + phone_no + ", gender=" + gender
				+ ", email=" + email + "]";
	}

	public Users() {
		
	}

	

	public void setUserid(long userid) {
		this.userid = userid;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(long phone_no) {
		this.phone_no = phone_no;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "name")
	private String name;
 
	@Column(name = "phone_no")
	private long phone_no;
	
	@Column(name = "gender")
	private String gender;

	@Column(name = "email")
	private String email;
	
	
	


	
}
