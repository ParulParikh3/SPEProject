package spring.event.model;

public class EventParser {
	
	private long eventid;
	private String eventname;

	public EventParser(long eventid,String eventname) {
		super();
		this.eventid=eventid;
		this.eventname=eventname;
		
	}
	
	public long getEventid() {
		return eventid;
	}

	public void setEventid(long eventid) {
		this.eventid = eventid;
	}

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

}
