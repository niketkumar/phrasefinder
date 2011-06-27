package com.activesphere.poc.phrasefinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: niket
 * Date: 27/06/11
 * Time: 9:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class IdiomsSource implements PhraseSource {

    private List<Idiom> idioms;

    public IdiomsSource() {
        idioms = new LinkedList<Idiom>();
    }

    @Override
    public IdiomsSource load(File sourceFile) throws Exception {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(sourceFile));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0) {
                    idioms.add(new Idiom(line));
                }
            }
        } finally {
            if (reader != null) reader.close();
        }
        return this;
    }

    @Override
    public Iterator<Idiom> iterator() {
        return idioms.iterator();
    }


}
