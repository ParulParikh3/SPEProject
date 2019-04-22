package spring.event.controller;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.event.model.Event;
import spring.event.model.EventCreationDetailsParser;
import spring.event.model.EventParser;
import spring.event.model.SpeakerSponsorDTO;
import spring.event.model.UserEventEmbedded;
import spring.event.model.User_EventLink;
import spring.event.repository.EventRepository;
import spring.event.repository.User_EventLinkRepository;

@RestController
@RequestMapping("/")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*",exposedHeaders="Access-Control-Allow-Origin")

public class EventController {
	
	private static String message="";

	@Autowired
	private EventRepository eventrepository;
	
	@Autowired
	private User_EventLinkRepository usereventrepository;
	
	@GetMapping("/events")
	public List<Event> getEvents()
	{
		List<Event> list=eventrepository.findByPhase("open");
		Collections.sort(list);
		return list ;
	}
	
	@PostMapping("/myevents/{id}")
	@ResponseBody
	public List<EventParser> getMyEvents(@PathVariable("id") long id,@RequestBody String role)
	{
		System.out.println(role);
		if(role=="participant") {
			List<EventParser> events=eventrepository.findByUserid_Phase(id,role,"open");
			Collections.sort(events);
			return events;
		}
		else {
			List<EventParser> events=eventrepository.findByUserid(id,role);
			Collections.sort(events);
			return events;
		}
	}
	
	@PostMapping("/myclosedevents/{id}")
	@ResponseBody
	public List<EventParser> getMyClosedEvents(@PathVariable("id") long id,@RequestBody String role)
	{
		
		System.out.println(eventrepository.findByUserid_Phase(id,role,"closed"));
		List<EventParser> events=eventrepository.findByUserid_Phase(id,role,"closed");
		Collections.sort(events);
		return events;
	}
	
	@PostMapping("listByStatus/{id}")
	@ResponseBody
	public List<EventParser> getListByStatus(@PathVariable("id") long id,@RequestBody String status )
	{
		System.out.println(status);
		List<EventParser> events=eventrepository.getListByStatus(id,status);
		Collections.sort(events);
		return events;
	}
	
	//used in organizer module(added by divya)
			@PostMapping("/saveEvent")
			public String saveEvent(@RequestBody EventCreationDetailsParser eventform)
			{
				String event_name=eventform.getEventname();
				if(eventrepository.findByEventname(event_name)!=null) 
				{
					message="event name already exist";
					return message;
				}
				long organizer_id=eventform.getOrganizerid();
				
				String eventdate=eventform.getEventdate();
				
				String description=eventform.getDescription();
				int participant_count=eventform.getParticipantcount();
				
				String lastdate=eventform.getLastdate();
				
				String eventlocation=eventform.getEventlocation();
				
				int fees=eventform.getEventfees();
				
				String typeofevent=eventform.getEventtype();
				
				Event event =new Event(event_name,eventdate,description,participant_count,eventlocation,fees,"creation",organizer_id,lastdate,typeofevent);
				eventrepository.save(event);
				Event new_event=eventrepository.findByEventname(event_name);
				UserEventEmbedded id=new UserEventEmbedded(organizer_id,new_event.getEventid());
				User_EventLink organizer=new User_EventLink(id,"organizer",0,"created");
				
				usereventrepository.save(organizer);
				message="successful";
				return message;
			}
			
			//used in organizer module(added by divya)
			@GetMapping("/undercreationevents")
			public Event getEventDetails(@RequestParam String name)
			{
				return eventrepository.findByEventname(name);
			}
			
			//used in organizer module(added by divya)
			@GetMapping("/organizerEvents")
			public List<SpeakerSponsorDTO> getUnderCreationEventDetails(@RequestParam long orgid,@RequestParam long eventid)
			{
				return eventrepository.findEventDetails(orgid,eventid);
				
			}
			
			
			
			//used in organizer module(added by divya)
		        @Transactional	
				@PostMapping("/updatePhase")
				public String updateEventPhase(@RequestBody String event_id)
				{
					
		        	long id=Long.parseLong(event_id);
					int speakercount=(int)(eventrepository.countOfSpeaker(id));
					int sponsorcount=(int)(eventrepository.countOfSponsor(id));
					
					if((speakercount==0) || (sponsorcount==0))
					{
						message="you can't update the phase because sufficicent speakers and sponsors are not available";
					}
					else 
					{
					eventrepository.UpdatePhase(id);
					message="phase of event updated successfully";}
					return message;
					
				}
}