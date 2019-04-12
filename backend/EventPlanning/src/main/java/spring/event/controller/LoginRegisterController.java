package spring.event.controller;

import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.event.model.Login;
import spring.event.model.LoginUserWrapper;
import spring.event.model.Users;
import spring.event.repository.LoginRepository;
import spring.event.repository.RegistrationRepository;

@RestController
@RequestMapping("/")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class LoginRegisterController {
	@Autowired
	private LoginRepository repo;
	@Autowired
	private RegistrationRepository reg;
  
@PostMapping(path="/login")
@ResponseBody
public long validate(@RequestBody Login login)
{
	Login log=repo.findByUsername(login.getUsername());
	if(log!=null)
	{
		if(log.getPass().equals(login.getPass()))
		{
			
		return log.getUserid();
		}
		else
			return 0;
		}
	else
		return 0;

		
	
}

@PostMapping(path="/create",produces="application/json")


public String add(@RequestBody LoginUserWrapper luw )
{
	if(repo.findByUsername(luw.getLogin().getUsername())!=null)
		return "username already exist";
	
	reg.save(luw.getUser());
	Users us=reg.findByEmail(luw.getUser().getEmail());
	Login lg=new Login();
	lg.setUsername(luw.getLogin().getUsername());
	lg.setPass(luw.getLogin().getPass());
	lg.setUserid(us.getUserid());
	repo.save(lg);
	return "Registration successful";
}




	 
}