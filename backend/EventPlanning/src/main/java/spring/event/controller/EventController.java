package spring.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private User_EventLinkRepository usereventrepository;
	
	private static String message="";
	
	@GetMapping("/events")
	public List<Event> getEvents()
	{
		return (List<Event>) eventRepository.findByPhase("open");
	}
	
	@GetMapping("/myevents/{id}")
	public List<Event> getMyEvents(@PathVariable("id") long id)
	{
		return (List<Event>) eventRepository.findByUserid(id);
	}
	
	@PostMapping("/myclosedevents/{id}")
	@ResponseBody
	public List<EventParser> getMyClosedEvents(@PathVariable("id") long id,@RequestBody String role)
	{
		
		
		return eventRepository.findByUserid_Phase(id,role,"closed");
		//return er.findByUserid(id,role);
	}
	
	@PostMapping("listByStatus/{id}")
	@ResponseBody
	public List<Event> getListByStatus(@PathVariable("id") int id,@RequestBody String status )
	{

		return (List<Event>) eventRepository.getListByStatus(id,status);
	}
	
	
	//used in organizer module(added by divya)
		@PostMapping("/saveEvent")
		public String saveEvent(@RequestBody EventCreationDetailsParser eventform)
		{
			String event_name=eventform.getEventname();
			if(eventRepository.findByEventname(event_name)!=null) 
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
			eventRepository.save(event);
			Event new_event=eventRepository.findByEventname(event_name);
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
			return eventRepository.findByEventname(name);
		}
		
		//used in organizer module(added by divya)
		@GetMapping("/organizerEvents")
		public List<SpeakerSponsorDTO> getUnderCreationEventDetails(@RequestParam long orgid,@RequestParam long eventid)
		{
			return eventRepository.findEventDetails(orgid,eventid);
			
		}
		
		//used in organizer module(added by divya)
	        @Transactional	
			@PostMapping("/updatePhase")
			public String updateEventPhase(@RequestBody String event_id)
			{
				long id=Long.parseLong(event_id);
				int speakercount=(int)(eventRepository.countOfSpeaker(id));
				int sponsorcount=(int)(eventRepository.countOfSponsor(id));
				
				if((speakercount==0) || (sponsorcount==0))
				{
					message="you can't update the phase because sufficicent speakers and sponsors are not available";
				}
				else 
				{
				eventRepository.UpdatePhase(id);
				message="phase of event updated successfully";}
				return message;
			}
	
	
}
