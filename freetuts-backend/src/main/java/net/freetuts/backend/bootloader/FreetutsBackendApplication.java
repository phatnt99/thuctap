package net.freetuts.backend.bootloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("net.freetuts.backend")
@EnableConfigurationProperties
@EntityScan(basePackages = {"net.freetuts.backend.entity"})
@EnableJpaRepositories(basePackages = "net.freetuts.backend.repository")
@SpringBootApplication
public class FreetutsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreetutsBackendApplication.class, args);
	}

}
