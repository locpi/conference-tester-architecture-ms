package tech.ippon.formation.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import tech.ippon.formation.microservices.config.ApplicationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class LiveCodingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiveCodingApplication.class, args);
    }

}
