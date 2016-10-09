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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainAppSpringConfig.class)
public class DocumentServiceTest {

	@Autowired
	private DocumentService documentService;
	
	@Test
	public void getDocumentTest() {
		assertThat(documentService.getDocument(), is(equalTo("Documentcontent")));
	}

	@Test
	public void getDocumentSpringTest() {
		assertThat(documentService.testSpring(), is(equalTo("Hello!")));
	}

}
