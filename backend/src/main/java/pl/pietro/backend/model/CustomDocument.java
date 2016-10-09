package pl.pietro.backend.model;

import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CustomDocument {
	
	private final Document htmlDocument;
	private final LinkedList<Page> pages = new LinkedList<>();
	private final Elements divElements;
	
	public CustomDocument(final Document htmlDocument, final Elements divElements) {
		super();
		this.htmlDocument = htmlDocument;
		this.divElements = divElements;
	}
	
}
