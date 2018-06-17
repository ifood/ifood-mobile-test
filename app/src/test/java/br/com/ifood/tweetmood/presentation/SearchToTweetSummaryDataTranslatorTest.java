package br.com.ifood.tweetmood.presentation;

import junit.framework.Assert;

import org.junit.Test;

import br.com.ifood.tweetmood.domain.model.TweetMetadataModel;
import br.com.ifood.tweetmood.domain.model.TweetSearchSummaryModel;
import br.com.ifood.tweetmood.mock.TwitterMock;
import br.com.ifood.tweetmood.presentation.util.translate.SearchToTweetMetadataTranslator;
import br.com.ifood.tweetmood.presentation.util.translate.SearchToTweetSummaryDataTranslator;
import br.com.ifood.tweetmood.presentation.util.translate.TranslatorException;

/**
 * Created by uchoa on 16/06/18.
 */

public class SearchToTweetSummaryDataTranslatorTest {
    
    @Test
    public void shouldTranslateReturnWithSuccess(){
        SearchToTweetSummaryDataTranslator translator = new SearchToTweetSummaryDataTranslator(new SearchToTweetMetadataTranslator());

        TweetSearchSummaryModel result = translator.translate(new TwitterMock().getSearchObject());


        Assert.assertNotNull("result must not be null", result);
        Assert.assertNotNull("tweets list should not be null", result.getTweets());
        Assert.assertEquals("name on metadata should be equals", "Peggy Whitson", result.getMetadata().getName());
        Assert.assertEquals("list should heve one item", 1, result.getTweets().size());
    }

    @Test( expected = TranslatorException.class)
    public void shouldTranslateReturnWithErrorWhenListOfTweetIsEmpty(){
        SearchToTweetMetadataTranslator translator = new SearchToTweetMetadataTranslator();

        TweetMetadataModel result = translator.translate(new TwitterMock().getSearchObjectWithEmptyTweets());
    }
}
