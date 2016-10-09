package pl.pietro.backend.tokenizer.dictionary;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import pl.pietro.backend.tokenizer.TokenMatcher.Dictionary;

public enum DocumentTitleType implements Dictionary {

	VEGETABLES("ziemniaki", "zielona pietruszka", "czerwona[ee] kapusta");

	private final Map<String, Pattern> textTagPatterns;

	DocumentTitleType(final String... textTags) {
		this.textTagPatterns = Arrays.stream(textTags).collect(Collectors.toMap(e -> e, e -> Pattern.compile(e)));
	}

	@Override
	public Map<String, Pattern> items() {
		return textTagPatterns;
	}

}
