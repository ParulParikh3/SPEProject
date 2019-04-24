package spring.event;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import spring.event.model.Event;
import spring.event.repository.EventRepository;



@SpringBootTest
@RunWith(SpringRunner.class)
public class EventPlanningApplicationTests {
	
	@Autowired
	ApplicationContext context;
	@Test
	public void contextLoads() {   
		EventRepository eventrepository=context.getBean(EventRepository.class);
	
		
		List<Event> list=(List<Event>)eventrepository.findAll();
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i).getEventname());
		}
		
			
	}

}
