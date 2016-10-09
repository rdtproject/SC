package pl.pietro.backend.anonymization;

import org.springframework.stereotype.Service;

@Service
public class HtmlService implements AnonymizationService {

	public String sayHello() {
		return "Hello!";
	}

	@Override
	public String anonymize(String document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String describe(String document) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
