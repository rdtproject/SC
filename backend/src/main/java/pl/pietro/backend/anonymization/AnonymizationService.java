package pl.pietro.backend.anonymization;

public interface AnonymizationService {

	/** Mask secret data from document */
	String anonymize(String document);

	/** Return information about document structure. Information should not contain secret data. */
	String describe(String document);

}
