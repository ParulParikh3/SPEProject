
// variables name must be same as defined in frontend 
package spring.event.model;

import javax.persistence.Column;

public class EventCreationDetailsParser {
	
	
	private String eventname;
	
	
	private String eventdate;
	
	
	private String description;
	
	
	private int participantcount;
	
	
	
	private String eventlocation;
	
	
	private int register_fee;
	
	
	public EventCreationDetailsParser(String eventname, String eventdate, String description, int participantcount,
			String eventlocation, int register_fee, long organizer_id, String last_date, String eventtype) {
		
		this.eventname = eventname;
		this.eventdate = eventdate;
		this.description = description;
		this.participantcount = participantcount;
		this.eventlocation = eventlocation;
		this.register_fee = register_fee;
		this.organizer_id = organizer_id;
		this.last_date = last_date;
		this.eventtype = eventtype;
	}

        public EventCreationDetailsParser()
	{
	}
	private long organizer_id;
	
	
	private String last_date;
	
	private String eventtype;

	


	public String getEventtype() {
		return eventtype;
	}
        
	public void setEventtype(String eventtype) {
		this.eventtype=eventtype;
	}

	public String getEventname() {
		return eventname;
	}
       
	public void setEventname(String eventname) {
		this.eventname=eventname;
	}

	public String getEventdate() {
		return eventdate;
	}
	
	public void setEventdate(String eventdate) {
		this.eventdate=eventdate;
	}


	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}


	public int getParticipantcount() {
		return participantcount;
	}
	
	public void setParticipantcount(int participantcount) {
		this.participantcount=participantcount;
	}


	public String getEventlocation() {
		return eventlocation;
	}
	
	public void setEventlocation(String eventlocation) {
		this.eventlocation=eventlocation;
	}


	public int getEventfees() {
		return register_fee;
	}
       
	public void setEventfees(int register_fee) {
	    this.register_fee=register_fee;
	}


	public long getOrganizerid() {
		return organizer_id;
	}
         public void setOrganizerid(long organizer_id) {
		this.organizer_id=organizer_id;
	}

	public String getLastdate() {
		return last_date;
	}
	public void setLastdate(String last_date) {
		this.last_date=last_date;
	}
	
}
