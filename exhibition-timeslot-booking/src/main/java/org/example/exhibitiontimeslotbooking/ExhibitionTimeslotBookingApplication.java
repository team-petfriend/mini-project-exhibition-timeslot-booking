package org.example.exhibitiontimeslotbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExhibitionTimeslotBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExhibitionTimeslotBookingApplication.class, args);
	}

}
