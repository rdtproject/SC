package pl.pietro.backend.tokenizer;

import java.util.function.Function;
import java.util.regex.Pattern;

import pl.pietro.backend.model.Token;
import pl.pietro.backend.tokenizer.TokenMatcher.Dictionary;
import pl.pietro.backend.tokenizer.TokenMatcher.TokenSupplier;
import pl.pietro.backend.tokenizer.dictionary.DocumentTitleType;
import pl.pietro.backend.tokenizer.token.DocumentTitle;

public enum TokenType {
	DOCUMENT_TITLE(DocumentTitleType.class, DocumentTitle::new),
	DATE("\\d{2}([./-])\\d{2}\\1\\d{4}", "\\d{2}([./-])\\d{2}\\1\\d{2}"),
	NUMBER("\\d+"),
	PUNCT("\\p{Punct}"),
	TEXT("\\p{IsAlphabetic}+"),
	UNKNOWN("\\S+");
	
	private final TokenMatcher matcher;

	TokenType(String... regexes) {
		this.matcher = new TokenMatcher.RegexTokenMatcher(this, regexes);
	}
	
	TokenType(Pattern...patterns) {
		this.matcher = new TokenMatcher.RegexTokenMatcher(this, patterns);
	}
	
	<D extends Enum<D> & Dictionary, T extends Token> TokenType(Class<D> enumType, TokenSupplier<T,D> factory) {
		this.matcher = new TokenMatcher.DictionaryTokenMatcher<>(enumType, factory);
	}
	
	TokenType(Function<String, String> function) {
		this.matcher = new TokenMatcher.CustomTokenMatcher(this, function);
	}
	
	TokenType(TokenMatcher matcher) {
		this.matcher = matcher;
	}
	
	public TokenMatcher matcher() {
		return matcher;
	}
	
	
	
}
