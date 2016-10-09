package pl.pietro.backend.configuration;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationSettings {

	private static final Logger Log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	private Environment environment;
	
	@Value("${NAME_VALUE}")
	private String name;

	public String getName() {
		return name;
	}
	
	
	
}
