package spring.event.controller;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.annotations.DynamicUpdate;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import spring.event.model.Event;
import spring.event.model.EventParser;
import spring.event.model.Login;
import spring.event.model.OptionalDetails;
import spring.event.model.UserEvent;
import spring.event.model.UserEventEmbedded;
import spring.event.model.User_EventLink;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.Resource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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
    private FileDownloadService fileStorageService;

	@Autowired
	private ServletContext context; 

	private static String message="";

// ---------------Divya-----------------------------
	@GetMapping("/speakerdetail/{eventid}")
	public List<RoleDetailDTO> speakerDetails(@PathVariable("eventid") long eventid)
	{
		
		List<RoleDetailDTO> speakerlist=optionaldetail.findSpeakerDetails(eventid);
		Collections.sort(speakerlist);
		return speakerlist;
		
	}
	
	@GetMapping("/sponsordetail/{eventid}")
	public List<RoleDetailDTO> sponsorDetails(@PathVariable("eventid") long eventid)
	{
		List<RoleDetailDTO> sponsorlist=optionaldetail.findSponsorDetails(eventid);
		Collections.sort(sponsorlist);
		return sponsorlist;
		
	}
	
	@PostMapping("/requesttospeaker")
	public String requestToSpeaker(@RequestBody UserEventEmbedded requestid)
	{
		
		if(usereventrepository.findByUsereventid(requestid)!=null)
		{
			User_EventLink receiver=usereventrepository. findByUsereventid(requestid);
			if(receiver.getRole().equals("speaker")&&receiver.getStatus().equals("requested"))
			{
				message="already sent a request to this speaker";
				return message;
			}
			else if(receiver.getRole().equals("organizer"))
				return "you can't send the request to this speaker because  either he is an organizer or send a request as an organizer for this event";
			
		}
		User_EventLink  request=new User_EventLink(requestid,"speaker",0,"requested");
		usereventrepository.save(request);
		message="success";
		return message;
		
	}
	
	@PostMapping("/requesttosponsor")
	public String requestToSponsor(@RequestBody UserEventEmbedded requestid)
	{
		
		if(usereventrepository.findByUsereventid(requestid)!=null)
		{
			User_EventLink receiver=usereventrepository. findByUsereventid(requestid);
			if(receiver.getRole().equals("sponsor")&&receiver.getStatus().equals("requested"))
			{
				message="already sent a request to this sponsor";
				return message;
			}
			
			else if(receiver.getRole().equals("organizer"))
				return "you can't send the request to this sponsor because  either he is an organizer or send a request as an organizer for this event";
		}
		User_EventLink  request=new User_EventLink(requestid,"sponsor",0,"requested");
		usereventrepository.save(request);
		message="success";
		return message;
		
	}
	
	//----------------------Divya and Parul--------------------------------
	@GetMapping("/downloadResume/{filename}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename,HttpServletRequest request) throws IOException {
        // Load file as Resource
		 System.out.println(filename);
		 Resource resource = fileStorageService.loadFileAsResource(filename);
		 
	        // Try to determine file's content type
	        String contentType = null;
	        try {
	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException ex) {
	            System.out.println("Could not determine file type.");
	        }

	        // Fallback to the default content type if type could not be determined
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	}
	
	//----------------------sowmya--------------------------------
	@GetMapping(path="/speaker", produces = { "application/json" })
	@ResponseBody
	public List<EventParser> getUnderconstructedEvents() {
		 
		List<EventParser> events;
		events =eventrepository.findEventsByPhase("creation");
		Collections.sort(events);
		return events;
	
	}
	
	@PostMapping(path="/eventdetails",consumes = {"application/json"}, produces = {"application/json"})
	@ResponseBody
	public Event getEventdetails(@RequestBody EventParser eventinfo){
		return eventrepository.findByEventid(eventinfo.getEventid());
	}
	
	@PostMapping("/checkevent")
	public String CheckExistingUser(@RequestBody UserEventEmbedded usereventid ){
		
		
		if(usereventrepository.findByUsereventid(usereventid)!=null) { 
			return failure;
		}
		else {
			return success;
		}
	}
	
	@GetMapping("/checkuser/{userid}")
	public String CheckExistingUser(@PathVariable("userid") long userid ){
		
		
		if(optionaldetail.findById(userid)!=null) { 
			return failure;
		}
		else {
			return success;
		}
	}
	
	/*------------------authorization before setting the role and uploading optional details-------------------*/
	
	@PostMapping(value="setRole", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> setRoleforEvent(@RequestParam(value="file",required=false) MultipartFile file, @RequestParam("userevent") String userevent) throws JsonParseException, JsonMappingException, IOException {
		
		
		UserEvent user = new ObjectMapper().readValue(userevent,UserEvent.class);

		OptionalDetails details = new OptionalDetails();
		
		

		if(user.getEventid()!=0) {
			UserEventEmbedded usereventid = new UserEventEmbedded();
			usereventid.setUserid(user.getUserid());
			usereventid.setEventid(user.getEventid());
			if(usereventrepository.findByUsereventid(usereventid)!=null) {
				return new ResponseEntity<>(failure,HttpStatus.OK);
			}
			else {
				User_EventLink newrole = new User_EventLink(usereventid,user.getRole(),0,"applied");
				usereventrepository.save(newrole);
			}
		}
		
		
		if(optionaldetail.findById(user.getUserid())!=null && user.getEventid()==0) {
			return new ResponseEntity<>(failure,HttpStatus.OK);
		}
		else {
			if(file!=null) {
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
				
				details.setResume(modifiedfilename);
			}
			
			if(user.getLinkedin()!="") {
				details.setLinkedin(user.getLinkedin());
			}
			if(user.getEducational_details()!="") {
				details.setEducational_details(user.getEducational_details());
			}
			details.setUserid(user.getUserid());
			details.setRole(user.getRole());
			optionaldetail.save(details);
			
		
			return new ResponseEntity<>(success,HttpStatus.OK); 
		}
	}




}

