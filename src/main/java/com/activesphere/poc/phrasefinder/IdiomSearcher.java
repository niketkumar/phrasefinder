package com.activesphere.poc.phrasefinder;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: niket
 * Date: 27/06/11
 * Time: 9:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class IdiomSearcher {
    private IndexSearcher searcher;

    public IdiomSearcher(Directory indexDirectory) throws IOException {
        searcher = new IndexSearcher(indexDirectory, true);
    }

    public ScoreDoc[] list(Query q) throws IOException {
        int hitsPerPage = 10;
        TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
        searcher.search(q, collector);
        ScoreDoc[] hits = collector.topDocs().scoreDocs;
        return hits;
    }

    public Document doc(int docId) throws IOException {
        return searcher.doc(docId);
    }
}
