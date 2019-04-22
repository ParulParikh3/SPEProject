
// variables name must be same as defined in frontend 
package spring.event.model;

import javax.persistence.Column;

public class EventCreationDetailsParser {
	
	public EventCreationDetailsParser()
	{}
	
	
	private String eventname;
	
	
	private String eventdate;
	
	
	private String description;
	
	
	public int getRegister_fee() {
		return register_fee;
	}


	public void setRegister_fee(int register_fee) {
		this.register_fee = register_fee;
	}


	public long getOrganizer_id() {
		return organizer_id;
	}


	public void setOrganizer_id(long organizer_id) {
		this.organizer_id = organizer_id;
	}


	public String getLast_date() {
		return last_date;
	}


	public void setLast_date(String last_date) {
		this.last_date = last_date;
	}


	public void setEventname(String eventname) {
		this.eventname = eventname;
	}


	public void setEventdate(String eventdate) {
		this.eventdate = eventdate;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setParticipantcount(int participantcount) {
		this.participantcount = participantcount;
	}


	public void setEventlocation(String eventlocation) {
		this.eventlocation = eventlocation;
	}


	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}


	private int participantcount;
	
	
	
	private String eventlocation;
	
	
	private int register_fee;
	
	
	public EventCreationDetailsParser(String eventname, String eventdate, String description, int participantcount,
			String eventlocation, int register_fee, long organizer_id, String last_date, String eventtype) {
		super();
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


	private long organizer_id;
	
	
	private String last_date;
	
	private String eventtype;

	


	public String getEventtype() {
		return eventtype;
	}


	public String getEventname() {
		return eventname;
	}


	public String getEventdate() {
		return eventdate;
	}


	public String getDescription() {
		return description;
	}


	public int getParticipantcount() {
		return participantcount;
	}


	public String getEventlocation() {
		return eventlocation;
	}


	public int getEventfees() {
		return register_fee;
	}


	public long getOrganizerid() {
		return organizer_id;
	}


	public String getLastdate() {
		return last_date;
	}


	@Override
	public String toString() {
		return "EventCreationDetailsParser [eventname=" + eventname + ", eventdate=" + eventdate + ", description="
				+ description + ", participantcount=" + participantcount + ", eventlocation=" + eventlocation
				+ ", register_fee=" + register_fee + ", organizer_id=" + organizer_id + ", last_date=" + last_date
				+ ", eventtype=" + eventtype + "]";
	}
	
	
	
	
}
