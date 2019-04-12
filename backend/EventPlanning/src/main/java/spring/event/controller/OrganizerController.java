package spring.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.event.repository.EventRepository;
import spring.event.repository.RegistrationRepository;
import spring.event.repository.User_EventLinkRepository;

import java.util.ArrayList;
import java.util.List;
import spring.event.model.Event;
import spring.event.model.UserEventEmbedded;
import spring.event.model.User_EventLink;
import spring.event.model.Users;

@RestController
@RequestMapping("/organizer")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class OrganizerController {
	
	@Autowired
	private EventRepository eventrepository;
	
	@Autowired
	private RegistrationRepository registrationrepository;
	
	@Autowired
	private User_EventLinkRepository usereventrepository;
	
	private static String message="";
	


	@GetMapping("/eventslist")
	public List<Event> getUnderConstructedEvents()
	{
	     return eventrepository.findByPhase("creation");

		
	}
	
	@GetMapping("/organizerDetail")
	public Users getOrganizerDetails(@RequestParam long organizer)
	{
		return registrationrepository.findByUserid(organizer);
	}

@PostMapping("/requestToOrganizer")
public String sendRequestToOrganizer(@RequestBody UserEventEmbedded usereventid)
{
	if(usereventrepository.findByUsereventid(usereventid)!=null)
	{
		User_EventLink sender=usereventrepository. findByUsereventid(usereventid);
		if(sender.getRole().equals("organizer")&&sender.getStatus().equals("requested"))
		{
			message="already sent a request";
			return message;
		}
		
	}
	User_EventLink sender=new User_EventLink(usereventid,"organizer",0,"requested");
	usereventrepository.save(sender);
	message="request sent successfully";
	return message;
	
}
	
	

}
