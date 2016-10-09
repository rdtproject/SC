package pl.pietro.backend.configuration;

import static pl.pietro.backend.configuration.AppConfigConstants.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class AppicationSettingsConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer defaultPropertyConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer.setLocations(new Resource[] {new ClassPathResource("profiles/default-application.ini")});
		return configurer;
	}

	@Configuration
	@Profile({DEFAULT, DEV})
	@PropertySources({@PropertySource("classpath:profiles/dev/application.ini")})
	public static class DevConfiguration {		
	}
}
