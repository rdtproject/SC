package pl.pietro.backend.conversion;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.Charsets;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pl.pietro.backend.model.CustomDocument;

public class HtmlToObject {

	public static final String PAGEWRAPPER = "pagewrapper";
	
	public static CustomDocument htmlStreamToObject(InputStream htmlContent) throws IOException {
		Document htmlDocument = Jsoup.parse(htmlContent, Charsets.ISO_8859_1.name(), "");
		preProcessHtmlDocument(htmlDocument);		
		
		CustomDocument objDocument = new CustomDocument(htmlDocument, getDocPagesElements(htmlDocument));
		
		return objDocument;
	}
	
	private static void preProcessHtmlDocument(Document htmlDocument) {
		htmlDocument.select("div[style~=left] > div[style=~=left]").forEach(elem -> elem.parentNode().after(elem));
	}
	
	public static List<String> getPagesAsText(Document htmlDocument) {
		return getDocPagesElements(htmlDocument).stream().map(Element::text).collect(Collectors.toList());
	}
	
	public static Elements getDocPagesElements(Document htmlDocument) {
		return htmlDocument.getElementsByAttributeValue("id", PAGEWRAPPER);
	}
	
}
