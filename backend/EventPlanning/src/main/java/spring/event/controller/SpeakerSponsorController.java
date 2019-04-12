package spring.event.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import spring.event.model.Event;
import spring.event.model.EventParser;
import spring.event.model.OptionalDetails;
import spring.event.model.UserEvent;
import spring.event.model.UserEventEmbedded;
import spring.event.model.User_EventLink;
import spring.event.repository.EventRepository;
import spring.event.repository.OptionalDetailsRepository;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import java.io.File;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import spring.event.model.Event;
import spring.event.model.EventParser;
import spring.event.model.OptionalDetails;
import spring.event.model.RoleDetailDTO;
import spring.event.model.UserEvent;
import spring.event.model.UserEventEmbedded;
import spring.event.model.User_EventLink;
import spring.event.repository.EventRepository;
import spring.event.repository.OptionalDetailsRepository;
import spring.event.repository.User_EventLinkRepository;

@RestController
@RequestMapping("/speakersponsor")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*",exposedHeaders="Access-Control-Allow-Origin")
public class SpeakerSponsorController {
	
	private static final String success= "successful";
	private static final String failure= "not possible";
	
	@Autowired
	private OptionalDetailsRepository optionaldetail;

	@Autowired
	private User_EventLinkRepository usereventrepository;
	
	@Autowired
	private EventRepository eventrepository;

	@Autowired
	private ServletContext context; 

	private static String message="";

// ---------------Divya-----------------------------
	@GetMapping("/speakerdetail")
	public List<RoleDetailDTO> speakerDetails()
	{
		List<RoleDetailDTO> speakerlist=optionaldetail.findSpeakerDetails();
		return speakerlist;
		
	}
	
	@GetMapping("/sponsordetail")
	public List<RoleDetailDTO> sponsorDetails()
	{
		List<RoleDetailDTO> sponsorlist=optionaldetail.findSponsorDetails();
		return sponsorlist;
		
	}
	
	@PostMapping("/requesttospeaker")
	public String requestToSpeaker(@RequestBody UserEventEmbedded requestid)
	{
		
		if(usereventrepository.findByUsereventid(requestid)!=null)
		{
			User_EventLink sender=usereventrepository. findByUsereventid(requestid);
			if(sender.getRole().equals("speaker")&&sender.getStatus().equals("requested"))
			{
				message="already sent a request to this speaker";
				return message;
			}
		}
		User_EventLink  request=new User_EventLink(requestid,"speaker",0,"requested");
		usereventrepository.save(request);
		message="request send successfully";
		return message;
		
	}
	@PostMapping("/requesttosponsor")
	public String requestToSponsor(@RequestBody UserEventEmbedded requestid)
	{
		
		if(usereventrepository.findByUsereventid(requestid)!=null)
		{
			User_EventLink sender=usereventrepository. findByUsereventid(requestid);
			if(sender.getRole().equals("sponsor")&&sender.getStatus().equals("requested"))
			{
				message="already sent a request to this sponsor";
				return message;
			}
		}
		User_EventLink  request=new User_EventLink(requestid,"sponsor",0,"requested");
		usereventrepository.save(request);
		message="request send successfully";
		return message;
		
	}
	
	//----------------------sowmya--------------------------------
	@GetMapping(path="/speaker", produces = { "application/json" })
	@ResponseBody
	public List<EventParser> getUnderconstructedEvents() {
		 
		List<EventParser> events;
		events =eventrepository.findEventsByPhase("creation");
		System.out.println(events.get(0).getEventname());
		return events;
	
	}
	
	@PostMapping(path="/eventdetails",consumes = {"application/json"}, produces = {"application/json"})
	@ResponseBody
	public Event getEventdetails(@RequestBody EventParser eventinfo){
		return eventrepository.findByEventid(eventinfo.getEventid());
	}
	
	@PostMapping("/checkevent")
	public String CheckExistingUser(@RequestBody UserEventEmbedded usereventid ){
		
		//UserEventEmbedded usereventid=new UserEventEmbedded(userid,eventid);
		
		
		if(usereventrepository.findByUsereventid(usereventid)!=null) { 
			return failure;
		}
		else {
			return success;
		}
	}
	
	@PostMapping(value="setRole", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> setRoleforEvent(@RequestParam("file") MultipartFile file, @RequestParam("userevent") String userevent) throws JsonParseException, JsonMappingException, IOException {
		System.out.println(userevent);
		
		//OptionalDetails details =new OptionalDetails();
		UserEvent user = new ObjectMapper().readValue(userevent,UserEvent.class);
	
		//System.out.println(user.getUserid());
		
		if(user.getEventid()!=0) {
			UserEventEmbedded usereventid = new UserEventEmbedded();
			usereventid.setUserid(user.getUserid());
			usereventid.setEventid(user.getEventid());
			
			//System.out.println(usereventid.getEventid());
			
			//System.out.println(user.getUserid());
			User_EventLink newrole = new User_EventLink(usereventid,user.getRole(),0,"applied");
			usereventrepository.save(newrole);
		}
		if(file.getOriginalFilename()!=null) {
			boolean exist = new File(context.getRealPath("/resumes")).exists();
			if(!exist) {
				new File(context.getRealPath("/resumes")).mkdir();
			}
			String filename=file.getOriginalFilename();
			String modifiedfilename=FilenameUtils.getBaseName(filename)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(filename);
			File storefile = new File(context.getRealPath("/resumes"+File.separator+modifiedfilename)); 
			try {
				FileUtils.writeByteArrayToFile(storefile,file.getBytes());
			}       
			
			catch(Exception e){
				e.printStackTrace();
			}
			OptionalDetails details = new OptionalDetails(user.getUserid(),modifiedfilename,user.getLinkedin(),user.getRole(),user.getEducational_details());
			optionaldetail.save(details);
		}
	
		return new ResponseEntity<>(success,HttpStatus.OK); 
	}




}

