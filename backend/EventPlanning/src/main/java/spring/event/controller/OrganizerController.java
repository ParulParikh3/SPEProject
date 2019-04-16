package spring.event.controller;


import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.event.model.Event;
import spring.event.model.UserEventEmbedded;
import spring.event.model.User_EventLink;
import spring.event.model.Users;
import spring.event.repository.EventRepository;
import spring.event.repository.RegistrationRepository;
import spring.event.repository.User_EventLinkRepository;

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
		List<Event> list=eventrepository.findByPhase("creation");
		Collections.sort(list);
	     return list;
		
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
				
				
		return "you can't  be an organizer because you either applied as a "+sender.getRole()+" for this event or already a "+sender.getRole() +" or organizer has already sent the request for "+sender.getRole()+" role";
				
				
			}
			User_EventLink sender=new User_EventLink(usereventid,"organizer",0,"applied");
			usereventrepository.save(sender);
			message="success";
			return message;
			
		}
	

}
