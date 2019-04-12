package spring.event.model;
import java.io.Serializable;
public class RoleDetailDTO implements Serializable{

	private long userid;
	private String name;
	private String email;
	private String educational_details;
	private String resume;
	private String linkedin;

	
	public String getEducational_details() {
		return educational_details;
	}
	public void setEducational_details(String educational_details) {
		this.educational_details = educational_details;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	public long getUserid() {
		return userid;
	}
	public String getName() {
		return name;
	}
	
	public String getResume() {
		return resume;
	}
	public String getEmail() {
		return email;
	}
	public String getEducation() {
		return educational_details;
	}
		
	public String getLinkedin() {
		return linkedin;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public RoleDetailDTO()
	{
		
	}
	
	public RoleDetailDTO(long userid, String name, String email,String educational_details,String linkedin,String resume) {
		super();
		this.userid = userid;
		this.name = name;
		this.email = email;
		this.educational_details=educational_details;
		this.resume=resume;
		this.linkedin=linkedin;
	}
	
	
}
