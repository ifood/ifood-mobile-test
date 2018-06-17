package br.com.ifood.tweetmood.presentation.util.translate;

/**
 * Created by uchoa on 12/06/18.
 */

public interface Translator <FROM, TO> {

    public TO translate(FROM from) throws TranslatorException;
}
