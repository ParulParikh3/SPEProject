package spring.event.model;

public class RoleDetailDTO implements Comparable<RoleDetailDTO >{

	private long userid;
	public RoleDetailDTO(long userid, String name, String email,String education,String linkedin,String resume) {
		super();
		this.userid = userid;
		this.name = name;
		this.email = email;
		this.education=education;
		this.resume=resume;
		this.linkedin=linkedin;
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
		return education;
	}
	private String name;
	private String email;
	private String education;
	private String resume;
	private String linkedin;
	
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
	@Override
	  public int compareTo(RoleDetailDTO u) {
	    if (getName() == null || u.getName() == null) {
	      return 0;
	    }
	    return getName().compareTo(u.getName());
	  }
	
	
}