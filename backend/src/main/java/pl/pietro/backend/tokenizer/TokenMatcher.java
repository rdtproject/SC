package pl.pietro.backend.tokenizer;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Strings;

import pl.pietro.backend.model.Token;

@FunctionalInterface
public interface TokenMatcher {
	
	Optional<Token> apply(String input, int start, int end);
	
	class RegexTokenMatcher implements TokenMatcher {
		private final Pattern[] patterns;
		private final TokenType type;

		public RegexTokenMatcher(TokenType type, String...regexp) {
			this(type, true, regexp);
		}
		
		public RegexTokenMatcher(TokenType type, boolean caseInsensitive, String... regexp) {
			this(type, Arrays.stream(regexp)
					.map(regex -> Pattern.compile(regex, caseInsensitive ? Pattern.CASE_INSENSITIVE : 0))
					.toArray(Pattern[]::new));
		}
		
		public RegexTokenMatcher(TokenType type, Pattern...patterns) {
			this.patterns = patterns;
			this.type = type;
		}
		
		@Override
		public Optional<Token> apply(String input, int start, int end) {
			return Arrays.stream(patterns).map(pattern -> {
				Matcher m = pattern.matcher(input).region(start, end).useTransparentBounds(true);
				Token token = null;
				if (m.lookingAt()) {
					token = new Token(m.group(), type, m.start(), m.end());
				}
				return token;
			}).filter(Objects::nonNull).findFirst();
		}
		
	}
	
	public interface Dictionary {
		Map<String, Pattern> items();
	}
	
	@FunctionalInterface
	public interface TokenSupplier<T extends Token, U extends Dictionary> {
		T get(String text, U type, int start, int end);
	}
	
	class DictionaryTokenMatcher<D extends Enum<D> & Dictionary, T extends Token> implements TokenMatcher {
		private final Class<D> enumType;
		private final TokenSupplier<T, D> factory;
		
		public DictionaryTokenMatcher(Class<D> enumType, TokenSupplier<T, D> factory) {
			this.enumType = enumType;
			this.factory = factory;
		}
		
		@Override
		public Optional<Token> apply(String input, int start, int end) {
			T token = null;
			for (D type : EnumSet.allOf(enumType)) {
				String prefix = matchPrefix(type, input, start, end);
				if (prefix != null && !prefix.isEmpty()) {
					token = factory.get(prefix, type, start, start + prefix.length()); 
				}
			}
			return Optional.ofNullable(token);
		}
		
		private static final Pattern EMPTY_PATTERN = Pattern.compile("");
		
		protected String matchPrefix (D type, String word, int start, int end) {
			Matcher m = EMPTY_PATTERN.matcher(Strings.nullToEmpty(word).toLowerCase())
					.region(start, end).useTransparentBounds(true);
			
			return type.items().keySet().stream()
					.filter(tag -> m.usePattern(type.items().get(tag)).lookingAt())
					.map(text -> m.group()).findFirst().orElse(null);
		}		
	}
	
	class CustomTokenMatcher implements TokenMatcher {
		private final TokenType type;
		private final Function<String, String> matcher;
		
		public CustomTokenMatcher(TokenType type, Function<String, String> matcher) {
			this.matcher = matcher;
			this.type = type;
		}
		
		@Override
		public Optional<Token> apply(String input, int start, int end) {
			return Optional.of(input.substring(start)).map(matcher).filter(Objects::nonNull)
					.map(m -> new Token(m, type, start, start + m.length()));
		}
	}
	
}
