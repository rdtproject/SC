package pl.pietro.backend.model;



import java.util.Collections;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import pl.pietro.backend.tokenizer.TokenType;

/**
 * @author rdt
 *
 */
public class Token extends Phrase {

	private final TokenType type;
	
	public Token(String text, TokenType type, int start, int end) {
		super(text, start, end, Collections.emptyList());
		this.type = type;
	}
	
	public TokenType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("text", getText())
				.append("type", type)
				.append("start", getStart())
				.append("end", getEnd())
				.append("previous", getPrevious())
				.append("next", getNext())
				.toString();
	}
	
}
