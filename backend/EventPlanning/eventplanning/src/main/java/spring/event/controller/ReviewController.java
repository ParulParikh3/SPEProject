package spring.event.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

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


import spring.event.model.Review;
import spring.event.model.ReviewParser;
import spring.event.model.Review_SuggestionParser;
import spring.event.model.UserEventEmbedded;
import spring.event.repository.ReviewRepository;

@RestController
@RequestMapping("/")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*",exposedHeaders="Access-Control-Allow-Origin")
public class ReviewController {
	
	private static final String success= "successful";
	private static final String failure= "not possible";
	
	@Autowired
	private ReviewRepository reviewrepository;
	
	@PostMapping("/checkReview")
	public String CheckExistingReview(@RequestBody UserEventEmbedded usereventid){
		
		
		if(reviewrepository.findReviewByUsereventid(usereventid)!=null) {
			return failure;
		}
		else {
			return success;
		}
	}
	
	
	/*------------------authorization before setting the review---------------------------*/
	
	@PostMapping(value="setReview", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> setReviewforEvent(@RequestBody String userreview) throws JsonParseException, JsonMappingException, IOException {
		System.out.println(userreview);
		
		ReviewParser reviewevent = new ObjectMapper().readValue(userreview,ReviewParser.class);

		UserEventEmbedded usereventid = new UserEventEmbedded();
		usereventid.setUserid(reviewevent.getUserid());
		usereventid.setEventid(reviewevent.getEventid());
		
		if(reviewrepository.findReviewByUsereventid(usereventid)!=null) {
			return new ResponseEntity<>(failure,HttpStatus.OK);
		}
		else {
			
			Review reviews = new Review(usereventid,reviewevent.getReview(),reviewevent.getSuggestion());
			reviewrepository.save(reviews);
		
			return new ResponseEntity<>(success,HttpStatus.OK); 
		}
	}
	
	@PostMapping("/getReview")
	@ResponseBody
	public ResponseEntity<List<Review_SuggestionParser>> getReview(@RequestBody UserEventEmbedded usereventid){
		
		List<Review_SuggestionParser> reviews = reviewrepository.getReviewsByUsereventid(usereventid.getEventid());
		return new ResponseEntity<>(reviews,HttpStatus.OK);
	}
	
	@PostMapping("/getSuggestion")
	@ResponseBody
	public ResponseEntity<List<Review_SuggestionParser>> getSuggestion(@RequestBody UserEventEmbedded usereventid){
		
		List<Review_SuggestionParser> suggestions = reviewrepository.getSuggestionsByUsereventid(usereventid.getEventid());
		return new ResponseEntity<>(suggestions,HttpStatus.OK);
	}
	
	
	
	
	
	

}
