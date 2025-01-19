package sentosa.sentosaspringserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SentosaSpringServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentosaSpringServerApplication.class, args);
	}

}
