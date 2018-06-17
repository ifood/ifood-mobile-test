package br.com.ifood.tweetmood.presentation.view.getuser.di;

import br.com.ifood.tweetmood.presentation.util.translate.SearchToTweetMetadataTranslator;
import br.com.ifood.tweetmood.presentation.util.translate.SearchToTweetSummaryDataTranslator;
import dagger.Module;
import dagger.Provides;

/**
 * Created by uchoa on 16/06/18.
 */

@Module
public class GetUserTweetsFragmentModule {

    @Provides
    SearchToTweetMetadataTranslator providesSearchToTweetMetadataTranslator(){
        return new SearchToTweetMetadataTranslator();
    }

    @Provides
    SearchToTweetSummaryDataTranslator providesSearchToTweetSummaryDataTranslator(SearchToTweetMetadataTranslator translator){
        return new SearchToTweetSummaryDataTranslator(translator);
    }
}
