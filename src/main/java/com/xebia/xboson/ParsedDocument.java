package com.xebia.xboson;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ParsedDocument {
    private List<SelectionRule> selectionRules = new ArrayList<>();
    private final Document doc;

    public ParsedDocument(Document doc) {
        this.doc = doc;

        selectionRules.add(new NameSelectionRule());
        selectionRules.add(new TagSelectionRule());
        selectionRules.add(new IdSelectionRule());

    }


    public List<ParsedElement> getElements() {
        Elements elements = doc.select("input, button, a, textarea");


        // Apply rules to make elements unique

        return elements.stream().map(this::match).filter(e -> e != null).collect(Collectors.toList());
    }

    private ParsedElement match(Element e) {
        for (SelectionRule rule : selectionRules) {
            Optional<ParsedElement> m = rule.match(doc, e);
            if (m.isPresent()) {
                return m.get();
            }
        }
        return null;
    }
}
