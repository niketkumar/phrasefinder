package com.activesphere.poc.phrasefinder;

import java.io.File;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: niket
 * Date: 27/06/11
 * Time: 9:32 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PhraseSource {
    PhraseSource load(File sourceFile) throws Exception;

    Iterator<? extends Phrase> iterator();
}
