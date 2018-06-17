package br.com.ifood.tweetmood.presentation.util.translate;

import com.twitter.sdk.android.core.models.Search;

import java.util.ArrayList;

import br.com.ifood.tweetmood.domain.Consts;
import br.com.ifood.tweetmood.domain.model.TweetModel;
import br.com.ifood.tweetmood.domain.model.TweetSearchSummaryModel;
import lombok.AllArgsConstructor;

/**O
 * Created by uchoa on 12/06/18.
 */

@AllArgsConstructor
public class SearchToTweetSummaryDataTranslator implements Translator<Search, TweetSearchSummaryModel> {

    private SearchToTweetMetadataTranslator translator;

    @Override
    public TweetSearchSummaryModel translate(Search search) throws TranslatorException{

        if(search == null || search.tweets == null)
            throw new TranslatorException(Consts.SEARCH_TO_TWEET_SUMMARY_DATA_EXCEPTION);

        ArrayList<TweetModel> tweets = new ArrayList<>();

        for(com.twitter.sdk.android.core.models.Tweet searchTweet : search.tweets) {
            tweets.add(TweetModel.builder()
                    .name(searchTweet.user.name)
                    .screenName("@" + searchTweet.user.screenName)
                    .urlProfile(searchTweet.user.profileImageUrl)
                    .text(searchTweet.text)
                    .build());
        }

        return TweetSearchSummaryModel.builder()
                .metadata(translator.translate(search))
                .tweets(tweets)
                .build();
    }
}
