package com.xebia.xboson;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Optional;

public class NameSelectionRule implements SelectionRule {
    @Override
    public Optional<ParsedElement> match(Document document, Element element) {
        String tagName = element.tagName();
        String name = element.attr("name");
        if ("".equals(name)) {
            return Optional.empty();
        }
        String cssQuery = String.format("%s[name=%s]", tagName, name);
        Elements found = document.select(cssQuery);
        if (found.size() == 1) {
            return Optional.of(new ParsedElement(name, cssQuery));
        }else {
            return Optional.empty();
        }
    }
}
