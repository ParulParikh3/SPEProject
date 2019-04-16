package spring.event;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import spring.event.model.Event;
import spring.event.repository.EventRepository;
@Component
public class CloseEventComponent {

	@Autowired
	private EventRepository eventrepository;
	
	
	
	@Scheduled(cron="0 1 0 * * ?")
	public void closeEvent() throws ParseException
	{
		List<Event> eventlist=eventrepository.findByPhase("open");
		Date currentDate=new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		Date format_currentdate=ft.parse(ft.format(currentDate));
	
		for(int i=0;i<eventlist.size();i++)
		{
			String Date=eventlist.get(i).getEventdate();
			Date eventDate=ft.parse(Date);  
		
			if(format_currentdate.compareTo(eventDate)>0)
			{
			eventlist.get(i).setPhase("closed");
			eventrepository.save(eventlist.get(i));
			
			
			}
		}
		
		
	}
	

}
