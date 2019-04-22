package spring.event.model;

public class SpeakerSponsorDTO {

	private String role;
	private String name;
	public String getRole() {
		return role;
	}
	public String getName() {
		return name;
	}
	public SpeakerSponsorDTO(String role, String name) {
		super();
		this.role = role;
		this.name = name;
	}
}
	
	