package pl.pietro.backend.behave;

import static org.hamcrest.Matchers.is;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.pietro.backend.api.DocumentService;

@Component
public class DocumentServiceBehavior {

	@Autowired
	private DocumentService documentService;
	
	int number1;
	int number2;
	
	@Given("Liczba o wartosci $value")
	public void given(String value) {
		number1 = Integer.parseInt(value);
	}
	
	@When("Mnoze liczbe przez $value")
	public void when(String value) {
		number2 = Integer.parseInt(value);
	}
	
	@Then("Wartosc to $value")
	public void then(String value) {
		Assert.assertThat(documentService.getDocument(), is(value));
	}
	
}
