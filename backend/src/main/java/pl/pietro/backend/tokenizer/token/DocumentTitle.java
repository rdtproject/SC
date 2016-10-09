package pl.pietro.backend.tokenizer.token;

import pl.pietro.backend.model.Token;
import pl.pietro.backend.tokenizer.TokenType;
import pl.pietro.backend.tokenizer.dictionary.DocumentTitleType;

public class DocumentTitle extends Token {
	private final DocumentTitleType labelType;
	
	public DocumentTitle(String text, DocumentTitleType type, int start, int end) {
		super(text, TokenType.DOCUMENT_TITLE, start, end);
		this.labelType = type;
	}
	
	public DocumentTitleType getLabelType() {
		return labelType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DocumentTitle [labelType=");
		builder.append(labelType);
		builder.append("]");
		return builder.toString();
	}
	
	
}
