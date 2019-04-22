package spring.event.model;

import javax.persistence.*;

@Entity
@Table(name="event")
public class Event implements Comparable<Event> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="eventid")
	private long eventid;
	
	@Column(name="organizer_id")
	private long organizer_id;
	
	@Column(name="eventname")
	private String eventname;
	
	@Column(name="eventdate")
	private String eventdate;
	
	@Column(name="description")
	private String description;
	
	@Column(name="participantcount")
	private int participantcount;
	
	@Column(name="participant_registered")
	private int participant_registered;
	
	@Column(name="eventlocation")
	private String eventlocation;
	
	@Column(name="waiting_count")
	private int waiting_count;
	
	@Column(name="register_fee")
	private int register_fee;

	@Column(name="phase")
	private String phase;
	
	@Column(name="eventtype")
	private String eventtype;
	
	@Column(name="last_date")
	private String last_date;
	
	
	
	
	public String getLast_date() {
		return last_date;
	}

	public void setLast_date(String last_date) {
		this.last_date = last_date;
	}

	public Event( String eventname, String eventdate, String description, int participantcount,
			String eventlocation, int register_fee, String phase,
			long organizer_id, String last_date, String eventtype) {
		super();
		
		this.eventname = eventname;
		this.eventdate = eventdate;
		this.description = description;
		this.participantcount = participantcount;
		
		this.eventlocation = eventlocation;
		this.register_fee = register_fee;
		this.phase = phase;;
		this.organizer_id = organizer_id;
		this.last_date = last_date;
		this.eventtype = eventtype;
	}
	
	public int getWaiting_count() {
		return waiting_count;
	}

	public void setWaiting_count(int waiting_count) {
		this.waiting_count = waiting_count;
	}
	
	




	public String getEventtype() {
		return eventtype;
	}




	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}




	public long getEventid() {
		return eventid;
	}


	

	public long getOrganizer_id() {
		return organizer_id;
	}




	public void setOrganizer_id(long organizer_id) {
		this.organizer_id = organizer_id;
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


	public String getEventdate() {
		return eventdate;
	}


	public void setEventdate(String eventdate) {
		this.eventdate = eventdate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getParticipantcount() {
		return participantcount;
	}


	public void setParticipantcount(int participantcount) {
		this.participantcount = participantcount;
	}


	public int getParticipant_registered() {
		return participant_registered;
	}


	public void setParticipant_registered(int participant_registered) {
		this.participant_registered = participant_registered;
	}


	public String getEventlocation() {
		return eventlocation;
	}


	public void setEventlocation(String eventlocation) {
		this.eventlocation = eventlocation;
	}


	public int getRegister_fee() {
		return register_fee;
	}


	public void setRegister_fee(int register_fee) {
		this.register_fee = register_fee;
	}


	public String getPhase() {
		return phase;
	}


	public void setPhase(String phase) {
		this.phase = phase;
	}

 public Event() {
		
	}


	@Override
	public String toString() {
		return "Event [eventid=" + eventid + ", eventname=" + eventname + ", eventdate=" + eventdate + ", description="
				+ description + ", participantcount=" + participantcount + ", participant_registered="
				+ participant_registered + ", eventlocation=" + eventlocation + ", register_fee=" + register_fee
				+ ", phase=" + phase + "]";
	}
	
	@Override
	  public int compareTo(Event u) {
	    if (getEventname() == null || u.getEventname() == null) {
	      return 0;
	    }
	    return getEventname().compareTo(u.getEventname());
	  }
	

	
}