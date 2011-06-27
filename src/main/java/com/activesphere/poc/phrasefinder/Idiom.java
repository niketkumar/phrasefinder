package com.activesphere.poc.phrasefinder;

import javax.xml.soap.Text;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: niket
 * Date: 27/06/11
 * Time: 9:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Idiom implements Phrase {

    private String text;
    private String description;

    public Idiom(String data) {
        StringTokenizer tokenizer = new StringTokenizer(data, ":");
        int tokenCount = tokenizer.countTokens();
        if (tokenCount == 2) {
            text = tokenizer.nextToken().trim();
            description = tokenizer.nextToken().trim();
//            System.out.println("Parsed idiom " + this);
        } else {
            throw new RuntimeException("Unparsable idiom data: " + data);
        }
    }

    public String getText() {
        return text;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Idiom{" +
                "text='" + text + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
