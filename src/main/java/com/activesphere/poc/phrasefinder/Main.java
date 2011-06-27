package com.activesphere.poc.phrasefinder;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: niket
 * Date: 27/06/11
 * Time: 9:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        RAMDirectory indexDirectory = new RAMDirectory();

        IdiomsIndexer indexer = new IdiomsIndexer();

        Set<String> stopWordsSet = new HashSet<String>((Collection<? extends String>) StandardAnalyzer.STOP_WORDS_SET);
        stopWordsSet.add("which");
        stopWordsSet.add("whom");
        stopWordsSet.add("where");
        stopWordsSet.add("who");
        stopWordsSet.add("why");
        stopWordsSet.add("whenever");
        stopWordsSet.add("something");
        stopWordsSet.add("some");
        stopWordsSet.add("somewhere");
        stopWordsSet.add("anything");
        stopWordsSet.add("very");

        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_32, stopWordsSet);

        indexer.index(indexDirectory,
                new IdiomsSource().load(new File(args[0])),
                new IndexWriterConfig(Version.LUCENE_32, analyzer)
        );

        Query q = indexer.buildQuery(args[1], analyzer);

        IdiomSearcher searcher = new IdiomSearcher(indexDirectory);
        ScoreDoc[] hits = searcher.list(q);

        System.out.println("Found " + hits.length + " hits.");
        for (int i = 0; i < hits.length; ++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            System.out.println((i + 1) + ". " + indexer.get(d));
        }
    }
}
