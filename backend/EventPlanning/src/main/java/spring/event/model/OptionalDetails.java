package spring.event.model;

import javax.persistence.*;
@Entity
@Table(name="optionaldetails")
public class OptionalDetails {
	
	@Id
	@Column(name="userid")
	private long userid;
	
	@Column(name="role")
	private String role;
	
	@Column(name="resume")
	private String resume;
	
	@Column(name="linkedin")
	private String linkedin;
	
	public OptionalDetails(long userid, String role, String resume, String linkedin, String education) {
		super();
		this.userid = userid;
		this.role = role;
		this.resume = resume;
		this.linkedin = linkedin;
		this.educational_details = education;
	}

	@Column(name="educational_details")
	private String educational_details;

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String getEducation() {
		return educational_details;
	}

	public void setEducation(String education) {
		this.educational_details = education;
	}

}
