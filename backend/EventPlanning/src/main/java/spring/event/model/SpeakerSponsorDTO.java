package spring.event.model;
import java.io.Serializable;

public class SpeakerSponsorDTO implements Serializable {

	private String role;
	private String name;
	public String getRole() {
		return role;
	}
	public String getName() {
		return name;
	}
	
	public SpeakerSponsorDTO()
	{}
	
	public SpeakerSponsorDTO(String role, String name) {
		super();
		this.role = role;
		this.name = name;
	}
	
	
}
