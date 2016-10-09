package pl.pietro.backend.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




public class Phrase extends DocumentElement {

	private String text;
	//private Block
	
	private final int start;
	private final int end;
	
	private Classification classification;
	
	private List<Phrase> subPhrases = new ArrayList<>();
	
	public Phrase(String text, int start, int end) {
		this(text, start, end, Collections.emptyList());
	}

	public Phrase(String text, int start, int end, List<Phrase> subPhrases) {
		super();
		this.text = text;
		this.start = start;
		this.end = end;
		this.subPhrases = subPhrases;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Classification getClassification() {
		return classification;
	}

	public void setSubPhrases(List<Phrase> subPhrases) {
		this.subPhrases = subPhrases;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}
	
	public boolean isClassified() {
		return classification != null;
	}
	
	public Phrase classifyAs(Classification classification) {
		this.classification = classification;
		return this;
	}
	
	public boolean classifiedAs(Classification classification) {
		return getClassification() == classification;
	}
	
	public List<Phrase> getSubPhrases() {
		return Collections.unmodifiableList(subPhrases);
	}
}
