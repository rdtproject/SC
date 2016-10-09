package pl.pietro.backend.tokenizer;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.pietro.backend.model.Token;

public class TokenizerTest {

	private Tokenizer tokenizer;
	
	@Before
	public void setUp() {
		tokenizer = new Tokenizer().withDefaults();
	}
	
	@Test
	public void testTokenizerWithDefaultConfiguration() {
		String text = "UBS, Buckhauserstrasse 41, 8048 Z\u00FCrich, Switzerland";		
		List<Token> tokens = tokenizer.tokenize(text);
		
		assertThat(tokens, is(notNullValue()));
		assertThat(tokens, hasSize(9));
		assertThat(tokens.get(0), hasProperty("text", is("UBS")));
		assertThat(tokens.get(0), hasProperty("type", is(TokenType.TEXT)));
		assertThat(tokens.get(1), hasProperty("text", is(",")));
		assertThat(tokens.get(1), hasProperty("type", is(TokenType.PUNCT)));
		assertThat(tokens.get(2), hasProperty("text", is("Buckhauserstrasse")));
		assertThat(tokens.get(2), hasProperty("type", is(TokenType.TEXT)));
		assertThat(tokens.get(3), hasProperty("text", is("41")));
		assertThat(tokens.get(3), hasProperty("type", is(TokenType.NUMBER)));
		assertThat(tokens.get(4), hasProperty("text", is(",")));
		assertThat(tokens.get(4), hasProperty("type", is(TokenType.PUNCT)));
		assertThat(tokens.get(5), hasProperty("text", is("8048")));
		assertThat(tokens.get(5), hasProperty("type", is(TokenType.NUMBER)));		
		assertThat(tokens.get(6), hasProperty("text", is("Z\u00FCrich")));
		assertThat(tokens.get(6), hasProperty("type", is(TokenType.TEXT)));
		assertThat(tokens.get(7), hasProperty("text", is(",")));
		assertThat(tokens.get(7), hasProperty("type", is(TokenType.PUNCT)));
		assertThat(tokens.get(8), hasProperty("text", is("Switzerland")));
		assertThat(tokens.get(8), hasProperty("type", is(TokenType.TEXT)));						
	}
	
//	public void shouldContainPhraseWithTokenTypeAccountNumber() {
//		String text = "ZULASTEN KONTO 301-914968.FKL chf";		
//		List<Token> tokens = tokenizer.tokenize(text);
//		
//		assertThat(tokens, is(notNullValue()));
//		assertThat(tokens, hasSize(4));
//		assertThat(tokens, hasItem(hasProperty("type", is(TokenType.ACCOUNT_NUMBER))));
//	}

	@Test
	public void shouldContainPhraseWithTokenTypeDate() {
		String[] dates = {"05.01.04", "05/01/04", "05-01-04", "05.01.2004", "05/01/2004", "05-01-2004"};
		
		for (String text : dates) {
			List<Token> tokens = tokenizer.tokenize(text);
			
			assertThat(tokens, is(notNullValue()));
			assertThat(tokens, hasSize(1));
 			assertThat(tokens, hasItem(hasProperty("type", is(TokenType.DATE))));
		}
	}
	
//	public void shouldContainPhraseWithTokenTypeAccountNumberEvenWithoutSurroundingSpaces() {
//		String text = "ZULASTEN KONTO301-914968.FKLCHF";		
//		List<Token> tokens = tokenizer.tokenize(text);
//		
//		assertThat(tokens, is(notNullValue()));
//		assertThat(tokens, hasSize(4));
//		assertThat(tokens, hasItem(hasProperty("type", is(TokenType.ACCOUNT_NUMBER))));		
//	}

//	public void shouldContainPhraseWithTokenTypeAmount() {
//		String text = "1 000 000.00";
//		List<Token> tokens = tokenizer.tokenize(text);
//		
//		assertThat(tokens, is(notNullValue()));
//		assertThat(tokens, hasSize(1));
//		
//		Token castedToken = tokens.get(0);
//		assertThat(castedToken.getType(), is(TokenType.AMOUNT));		
//	}
	
//	public void shouldNotContainPhraseWithTokenTypeAmount() {
//		String text = "03.06.10";
//		List<Token> tokens = tokenizer.tokenize(text);
//		
//		assertThat(tokens, is(notNullValue()));
//		assertThat(tokens, not(hasItem(hasProperty("type"),is(TokenType.AMOUNT))));
//	}
	
}
