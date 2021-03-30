package pl.espeo.sampleproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleProjectApplication {

	public static void main(String[] args) {
		System.out.println("~x = " + ~5);
		SpringApplication.run(SampleProjectApplication.class, args);
	}
}
