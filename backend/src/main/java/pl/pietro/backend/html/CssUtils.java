package pl.pietro.backend.html;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CssUtils {

	public static final String FONT_FAMILY = "font-family";
	public static final String FONT_SIZE = "font-size";
	public static final String FONT_WEIGHT = "font-weight";
	
	private CssUtils() {		
	}
	
	public static Map<String, String> parseCssStyle(String style) {
		Objects.requireNonNull(style);
		return Arrays.stream(style.toLowerCase().split("\\s*;\\s*"))
				.map(t -> t.split("\\s*:\\s*"))
				.filter(t -> t.length == 2)
				.collect(Collectors.toMap(t -> t[0], t -> t[1]));
	}
	
	public static int getInt(Map<String, String> style, String key) {
		return Integer.parseInt(style.getOrDefault(key, "0").replaceAll("px.*", ""));
	}
	
	public static int getTextWidth(Map<String, String> style, String text) {
		FontMetrics metrics = getFontMetrics(style);
		return metrics.stringWidth(text);
	}
	
	public static int getTextHeight(Map<String, String> style) {
		FontMetrics metrics = getFontMetrics(style);
		return metrics.getHeight();
	}
	
	private static FontMetrics getFontMetrics(Map<String, String> style) {
		String fontFamily = Objects.requireNonNull(style.get(FONT_FAMILY));
		int fontSize = getInt(style, FONT_SIZE);
		
		Font font = new Font(fontFamily, Font.PLAIN, fontSize);
		
		Canvas c = new Canvas();
		return c.getFontMetrics(font);
	}
		
}
