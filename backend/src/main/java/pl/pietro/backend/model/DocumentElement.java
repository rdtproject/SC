package pl.pietro.backend.model;

import java.util.Optional;

public class DocumentElement {

	private Optional<DocumentElement> previous = Optional.empty();
	private Optional<DocumentElement> next = Optional.empty();
	
	public DocumentElement getPrevious() {
		return previous.orElse(null);
	}
	
	public void setPrevious(DocumentElement previous) {
		this.previous = Optional.ofNullable(previous);
		setCurrentAsNextForPrevious(previous);
	}
	
	public DocumentElement getNext() {
		return next.orElse(null);		
	}
	
	public void setNext(DocumentElement next) {
		this.next = Optional.ofNullable(next);
		setCurrentAsPreviousForNext(next);
	}
	
	private void setCurrentAsNextForPrevious(DocumentElement previous) {
		Optional.ofNullable(previous).ifPresent(element -> element.next = Optional.of(this));
	}
	
	private void setCurrentAsPreviousForNext(DocumentElement next) {
		Optional.ofNullable(next).ifPresent(element -> element.previous = Optional.of(this));
	}
}
