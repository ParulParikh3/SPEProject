package spring.event.model;
import java.io.Serializable;
public class Review_SuggestionParser implements Serializable  {
	
	private String username;
	private String comment;
	private static final long serialVersionUID=4L;
	
	public Review_SuggestionParser(){
		
	}
	
	public Review_SuggestionParser(String username,String comment){
		this.username=username;
		this.comment=comment;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}
