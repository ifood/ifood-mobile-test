package br.com.ifood.tweetmood.data.repositories.contract;

import android.arch.lifecycle.LiveData;

import com.twitter.sdk.android.core.models.Search;

import br.com.ifood.tweetmood.domain.model.WrapperResponse;

/**
 * Created by uchoa on 14/06/18.
 */

public interface TwitterDataSource {

    LiveData<WrapperResponse<Search>> getTwitterSearchData(String username);

    LiveData<WrapperResponse<Search>> getTwitterSearchDataNextPage(String username, String nextPage);

}
