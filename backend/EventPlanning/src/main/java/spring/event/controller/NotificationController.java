package spring.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import spring.event.repository.User_EventLinkRepository;

@RestController
@RequestMapping("/")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*",exposedHeaders="Access-Control-Allow-Origin")
public class NotificationController {
	
String success="Success";
	
	@Autowired
	private User_EventLinkRepository usereventrepository;
	
	
	@GetMapping("notification/{id}")
	public List<String> getMyEvents(@PathVariable("id") int id)
	{
	
		 return  usereventrepository.findByStatusRequest(id);
		
	}
	
	
	@PostMapping("statusChange/{id}")
	@ResponseBody
	public String ChangeStatus(@PathVariable("id") int id,@RequestBody String status )
	{
		System.out.println(status);
			usereventrepository.changeStatus(id,status);
			return success;
	}

	@RequestMapping(value = "requestspeakers/{id}", method = RequestMethod.GET)
	public List<Object[]> getRequestingSpeakers(@PathVariable(value = "id") int id)
	{
		System.out.println(id);
		return usereventrepository.requestsFromSpeaker(id);
	}
	
	@RequestMapping(value = "requestsponsor/{id}", method = RequestMethod.GET)
	public List<Object[]> getRequestingSponsor(@PathVariable(value = "id") int id)
	{
		System.out.println(id);
		return usereventrepository.requestsFromSponsor(id);
	}
	
	@GetMapping("requestfromorganiser/{id}")
	public List<Object> getNameOfOrganiserRequesting(@PathVariable("id") int id)
	{
	
		 return  usereventrepository.findByOrganizerRequest(id);
		
	}
	
	

}