package pl.pietro.backend.api;

import java.lang.invoke.MethodHandles;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pietro.backend.anonymization.HtmlService;

@Service
public class DocumentService {

	final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private HtmlService afpService;
	
	@Autowired
	public DocumentService(HtmlService afpService) {
		this.afpService = afpService;
	}
	
	public String testSpring() {
		return afpService.sayHello();
	}
	
	public String getDocument() {
		logger.info("getDocument - returning value");
		return StringUtils.deleteWhitespace("Document content");
	}
	
}
