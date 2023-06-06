package hjh.spring.POS;

import hjh.spring.POS.configuration.JavaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class POSApplication
{

	public static void main(String[] args) {
		SpringApplication.run(POSApplication.class, args);
	}

}
