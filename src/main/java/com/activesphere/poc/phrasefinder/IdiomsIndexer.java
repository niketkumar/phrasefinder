package com.activesphere.poc.phrasefinder;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;

import java.util.Iterator;

/**
 * User: niket
 * Date: 27/06/11
 * Time: 9:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class IdiomsIndexer {

    public void index(Directory indexDirectory, IdiomsSource idiomsSource, IndexWriterConfig indexWriterConfig) throws Exception {
        IndexWriter w = null;
        try {
            w = new IndexWriter(indexDirectory, indexWriterConfig);
            Iterator<Idiom> iterator = idiomsSource.iterator();
            while (iterator.hasNext()) {
                Idiom idiom = iterator.next();
                w.addDocument(createDocument(idiom));
            }
        } finally {
            if (w != null) w.close();
        }
    }

    private Document createDocument(Idiom idiom) {
        Document document = new Document();
        document.add(new Field("idiom", idiom.getText(), Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("desc", idiom.getDescription(), Field.Store.NO, Field.Index.ANALYZED));
        return document;
    }

    public Query buildQuery(String queryStr, Analyzer analyzer) throws ParseException {
        return new QueryParser(Version.LUCENE_32, "desc", analyzer).parse(queryStr);
    }

    public String get(Document d) {
        return d.get("idiom");
    }
}
