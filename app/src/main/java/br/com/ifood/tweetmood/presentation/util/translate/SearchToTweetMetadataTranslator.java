package br.com.ifood.tweetmood.presentation.util.translate;

import com.twitter.sdk.android.core.models.Search;

import br.com.ifood.tweetmood.domain.Consts;
import br.com.ifood.tweetmood.domain.model.TweetMetadataModel;

/**
 * Created by uchoa on 12/06/18.
 */

public class SearchToTweetMetadataTranslator implements Translator<Search, TweetMetadataModel> {

    @Override
    public TweetMetadataModel translate(Search search) throws TranslatorException {

        if(search == null || search.tweets == null || search.tweets.isEmpty())
            throw new TranslatorException(Consts.SEARCH_TO_TWEET_METADATA_EXCEPTION);

        return TweetMetadataModel.builder()
                .completedIn(search.searchMetadata.completedIn)
                .count(search.searchMetadata.count)
                .maxId(search.searchMetadata.maxId)
                .maxIdStr(search.searchMetadata.maxIdStr)
                .nextResults(search.searchMetadata.nextResults)
                .query(search.searchMetadata.query)
                .refreshUrl(search.searchMetadata.refreshUrl)
                .sinceId(search.searchMetadata.sinceId)
                .sinceIdStr(search.searchMetadata.sinceIdStr)
                .name(search.tweets.get(0).user.name)
                .username(search.tweets.get(0).user.screenName)
                .url(search.tweets.get(0).user.profileImageUrl)
                .build();
    }
}
