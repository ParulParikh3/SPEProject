package spring.event.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.event.model.Event;
import spring.event.model.EventRegistrationDetailParser;
import spring.event.model.UserEventEmbedded;
import spring.event.model.User_EventLink;
import spring.event.model.Waiting_List;
import spring.event.repository.EventRepository;
import spring.event.repository.User_EventLinkRepository;
import spring.event.repository.WaitingListRepository;



@RestController
@RequestMapping("/participant")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*",exposedHeaders="Access-Control-Allow-Origin")
public class ParticipantController {

	@Autowired
	private EventRepository eventrepository;
	
	@Autowired
	private User_EventLinkRepository usereventrepository;
	
	@Autowired
	private WaitingListRepository waitingrepository;
	
	@GetMapping("/eventdetail")
	public Event getEvent(@RequestParam String name)
	{
		
	
		return eventrepository.findByEventname(name);
			
	}
	
	@Transactional
	@PostMapping("/register")
	public String registerParticipant(@RequestBody EventRegistrationDetailParser eventdetail )
	{
		
		UserEventEmbedded usereventid=new UserEventEmbedded(eventdetail.getUserid(),eventdetail.getEventid());
		
		
		if(usereventrepository.findByUsereventid(usereventid)!=null) //if userid and eventid present  in user_eventlink table
		{
			User_EventLink user=usereventrepository.findByUsereventid(usereventid);
			if(user.getRole()=="participant")
			return "already registered for this event";
			
			else
				return "You can't register as a participant beacause you are "+user.getRole()+" for this event";
			
		}
		
		else
		{
		User_EventLink data;
        Event et=eventrepository.findByEventid(eventdetail.getEventid()); 
        String status;
		if((et.getParticipant_registered()+1)<=et.getParticipantcount())
		{
			status="approved";
			data=new User_EventLink(usereventid,eventdetail.getRole(),eventdetail.getFees(),status); 
			usereventrepository.save(data);   //insert data in user_eventlink table
		int new_count=et.getParticipant_registered()+1; // update participant_registered attribute in event table
		et.setParticipant_registered(new_count);
		eventrepository.save(et);
		return "registration success";
		}	
		else      
			
		{
			status="waiting";
			data=new User_EventLink(usereventid,eventdetail.getRole(),eventdetail.getFees(),status);
			usereventrepository.save(data); //insert data in user_eventlink table with status="waiting"
			Waiting_List wait=new Waiting_List(usereventid.getUserid(),usereventid.getEventid());
			waitingrepository.save(wait); // insert into waiting_list table
			int wait_count=et.getWaiting_count()+1;
			et.setWaiting_count(wait_count); //update wait_count in event table
			eventrepository.save(et);
		 return "in waiting list";
		 }
		}
		
	}
	
	@Transactional
	@PostMapping("/withdraw")
	public String withdrawParticipant(@RequestBody UserEventEmbedded  id)
	{
		User_EventLink userevent=usereventrepository.findByUsereventid(id);
		Event event=eventrepository.findByEventid(id.getEventid());
		if(userevent!=null && userevent.getStatus().equals("approved")) 
		{
			usereventrepository.deleteById(id); // delete entry from user_eventlink table
		   if(event.getWaiting_count()!=0)    // case where there are participants in waiting list
		   {
			   int wait_count=event.getWaiting_count()-1;  //update wait_count in event table since on waiting list participant get approved
			   event.setWaiting_count(wait_count);
			   eventrepository.save(event);
	//find userid from witing_list table that registered first for the particular event		   
			   Waiting_List wl=waitingrepository.findByeventid(id.getEventid());
			// adding that userid in user_eventlink table  with status="approved"
			   User_EventLink new_participant=new  User_EventLink();
			  id.setEventid(wl.getEventid());
			  id.setUserid(wl.getUserid());
			   new_participant.setUsereventid(id);
			   new_participant.setAmountpaid(event.getRegister_fee());
			   new_participant.setRole("participant");
			   new_participant.setStatus("approved");
			   usereventrepository.save(new_participant);
			   waitingrepository.delete(wl); // delete that userid from waitingList table since it is approved now
			   return "withdrawn successfully";
			   
			   // in this case there is no need to update participant_registered beacuse new participant is adding here
		   }
			
		   else {    // case when no participant in waiting for this event
			
				int registeredcount=event.getParticipant_registered()-1;  //update participant_registered 
				event.setParticipant_registered(registeredcount);
				eventrepository.save(event);
				return "withdrawn successfully";
		}
		   }
		
		else if(userevent!=null && userevent.getStatus().equals("waiting"))
		{
			usereventrepository.deleteById(id);  // deleting entry fron user_eventlink table 
			Waiting_List waitlist=waitingrepository.findByUserandEventid(id.getUserid(),id.getEventid());
			waitingrepository.deleteById(waitlist.getWaiting_id()); // deleting entry from waiting_list also
			event.setWaiting_count(event.getWaiting_count()-1); // updating waiting_count in event table
			eventrepository.save(event);
		return "withdrawn successfully";
		}
		
		else
			return "not registered participant"; // case if unregistered user try to click withdraw button
		
	}
	
	@PostMapping("/status")
	public String statusCheck(@RequestBody UserEventEmbedded  id)
	{
		User_EventLink userevent=usereventrepository.findByUsereventid(id);
		if(userevent!=null)
			return userevent.getStatus();
		
		else
			return "not available";
	}
	
}


