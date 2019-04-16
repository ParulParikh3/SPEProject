package spring.event.model;

public class EventParser  implements Comparable<EventParser>  {
	
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
	
	@Override
	  public int compareTo(EventParser u) {
	    if (getEventname() == null || u.getEventname() == null) {
	      return 0;
	    }
	    return getEventname().compareTo(u.getEventname());
	  }

}
