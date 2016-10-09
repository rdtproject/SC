package pl.pietro.backend;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {"pl.pietro.backend" },
	excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "pl.pietro.backend.behave.*") )
public class MainAppSpringConfig {

}
