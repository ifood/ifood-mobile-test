package br.com.ifood.tweetmood.presentation.base;

import android.support.v4.app.Fragment;

/**
 * Created by uchoa on 14/06/18.
 */

public interface BaseActivityView {

    void replaceFragment(Fragment fragment);

    void finishApplicationWithErrorMessage(int message);

}
