package uy.edu.bios.obligatorio.bios_work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:values.properties")
public class BiosWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiosWorkApplication.class, args);
	}

}