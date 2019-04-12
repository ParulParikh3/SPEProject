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
	private User_EventLinkRepository nr;
	
	
	@GetMapping("notification/{id}")
	public List<String> getMyEvents(@PathVariable("id") int id)
	{
	
		 return  nr.findByStatusRequest(id);
		
	}
	
	
	@PostMapping("statusChange/{id}")
	@ResponseBody
	public String ChangeStatus(@PathVariable("id") int id,@RequestBody String status )
	{
			nr.changeStatus(id,status);
			return success;
	}
	
	//@GetMapping("requestspeakers/{id}")
	@RequestMapping(value = "requestspeakers/{id}", method = RequestMethod.GET)
	public List<Object[]> getRequestingSpeakers(@PathVariable(value = "id") int id)
	{
		
		return nr.requestsFromSpeaker(id);
	}
	
	@RequestMapping(value = "requestsponsor/{id}", method = RequestMethod.GET)
	public List<Object[]> getRequestingSponsor(@PathVariable(value = "id") int id)
	{
	
		return nr.requestsFromSponsor(id);
	}

	

}
