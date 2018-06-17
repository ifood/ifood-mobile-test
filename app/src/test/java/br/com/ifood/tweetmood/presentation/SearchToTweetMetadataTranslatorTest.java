package br.com.ifood.tweetmood.presentation;

import junit.framework.Assert;

import org.junit.Test;

import br.com.ifood.tweetmood.domain.model.TweetMetadataModel;
import br.com.ifood.tweetmood.mock.TwitterMock;
import br.com.ifood.tweetmood.presentation.util.translate.SearchToTweetMetadataTranslator;
import br.com.ifood.tweetmood.presentation.util.translate.TranslatorException;

/**
 * Created by uchoa on 16/06/18.
 */

public class SearchToTweetMetadataTranslatorTest {

    @Test
    public void shouldTranslateReturnWithSuccess(){
        SearchToTweetMetadataTranslator translator = new SearchToTweetMetadataTranslator();

        TweetMetadataModel result = translator.translate(new TwitterMock().getSearchObject());

        Assert.assertNotNull("result must not be null", result);
    }

    @Test( expected = TranslatorException.class)
    public void shouldTranslateReturnWithErrorWhenListOfTweetIsEmpty(){
        SearchToTweetMetadataTranslator translator = new SearchToTweetMetadataTranslator();

        TweetMetadataModel result = translator.translate(new TwitterMock().getSearchObjectWithEmptyTweets());
    }
}
