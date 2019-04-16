package spring.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import spring.event.model.FileStorageProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class EventPlanningApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventPlanningApplication.class, args);
		
	}

}
