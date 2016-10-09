package pl.pietro.standalone;

import static org.hamcrest.Matchers.is;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class BloomFilterBehavior {

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
		Assert.assertThat(number1 * number2, is(Integer.parseInt(value)));
	}
	
}
