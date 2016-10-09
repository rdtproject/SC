package pl.pietro.backend.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.pietro.backend.MainAppSpringConfig;
import pl.pietro.backend.configuration.ApplicationSettings;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainAppSpringConfig.class)
public class ApplicationSettingsTest {
	
	@Autowired
	private ApplicationSettings appSettings;

	@Test
	public void getNameShouldReturnKowalski() {
		assertThat(appSettings.getName(), is(equalTo("Kowalski")));
	}
}
