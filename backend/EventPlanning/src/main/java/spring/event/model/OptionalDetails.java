package spring.event.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
@Entity
@Table(name="optionaldetails")
public class OptionalDetails {
	@Id
	@Column(name="userid")
	private long userid;
	
	@Column(name="resume")
	private String resume;
	
	@Column(name="linkedin")
	private String linkedin;
	
	@Column(name="role")
	private String role;
	
	@Column(name="educational_details")
	private String educational_details;
	


	public OptionalDetails(){
		
	}
	
	public OptionalDetails(Long userid,String resume, String linkedin, String role,String educational_details) {
		this.userid=userid;
		this.resume=resume;
		this.linkedin=linkedin;
		this.role=role;
		this.educational_details=educational_details;
	}
	
	
	public String getEducational_details() {
		return educational_details;
	}

	public void setEducational_details(String educational_details) {
		this.educational_details = educational_details;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

}
