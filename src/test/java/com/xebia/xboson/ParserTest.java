package com.xebia.xboson;

import org.hamcrest.CoreMatchers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by xviuda on 22-01-16.
 */
public class ParserTest {

    @Test
    public void shouldParseADocument() throws IOException {

        // Parser document parsertest.html
        ParsedDocument doc = parseDocument(new File("src/test/resources/parsertest.html"));

        // Vraag om gevonden elementen
        List<ParsedElement> elements = doc.getElements();
        // Elementen bevat input@name=foo
        assertThat(elements.size(), is(1));
        assertThat(elements.get(0).name(), is("input@name=foo"));

    }

    private ParsedDocument parseDocument(File input) throws IOException {
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        return new ParsedDocument(doc);
    }
}