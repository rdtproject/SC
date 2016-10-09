package pl.pietro.backend.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;

public class PageElement extends DocumentElement {

	protected final Page page;
	protected final Element div;
	protected final int top;
	protected final int left;
	protected final Map<String, String> style;
	
	public PageElement(Page page, Element div, int xPosition, int yPosition, Map<String, String> style) {
		this.page = page;
		this.div = div;
		this.top = yPosition;
		this.left = xPosition;
		this.style = style;
	}

	public Page getPage() {
		return page;
	}

	public Element getDiv() {
		return div;
	}

	public int getTop() {
		return top;
	}

	public int getLeft() {
		return left;
	}

	public Map<String, String> getStyle() {
		return style;
	}
	
	public List<TextNode> getAllTextNodes() {
		return this.div.textNodes();
	}
	
	public List<TextNode> getNotBlankTextNodes() {
		return this.div.textNodes().stream().filter(textNode -> !textNode.isBlank()).collect(Collectors.toList());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + left;
		result = prime * result + ((page == null) ? 0 : page.hashCode());
		result = prime * result + top;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageElement other = (PageElement) obj;
		if (left != other.left)
			return false;
		if (page == null) {
			if (other.page != null)
				return false;
		} else if (!page.equals(other.page))
			return false;
		if (top != other.top)
			return false;
		return true;
	}
	
	
	
}
