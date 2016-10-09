package pl.pietro.backend.tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.pietro.backend.model.Token;

public class Tokenizer {

	public static final Tokenizer INSTANCE = new Tokenizer().withDefaults();
	private List<TokenMatcher> matchers = new LinkedList<>();
	
	public Tokenizer matcher(TokenMatcher matcher) {
		matchers.add(matcher);
		return this;
	}
	
	private static final Pattern WHITESPACES = Pattern.compile("\\s*");
	
	private int skip(String input, int start, Pattern pattern) {
		Matcher matcher = pattern.matcher(input).region(start, input.length());
		return matcher.lookingAt() ? matcher.end() : start;
	}
	
	public Tokenizer withDefaults() {
		Arrays.stream(TokenType.values()).map(TokenType::matcher).forEach(this::matcher);
		return this;
	}
	
	public List<Token> tokenize(String input) {
		Comparator<Token> comparator = Comparator.comparingInt(Token::getStart);
		return new ArrayList<>(tokenize(input, 0, input.length(), new TreeSet<>(comparator)));
	}
	
	private TreeSet<Token> tokenize(String input, int start, int end, TreeSet<Token> tokens) {
		nextToken(input, skip(input, start, WHITESPACES), end).ifPresent(token -> {
			tokens.add(token);
			if (start < token.getStart()) tokenize(input, start, token.getStart(), tokens);
			if (token.getEnd() < end) tokenize(input, token.getEnd(), end, tokens);
		});
		return tokens;
	}
	
	private Optional<Token> nextToken(String input, int start, int end) {
		Optional<Token> token = Optional.empty();
		for (TokenMatcher matcher : matchers) {
			token = matcher.apply(input, start, end);
			if (token.isPresent()) {
				break;
			}
		}
		return token;
	}
}
