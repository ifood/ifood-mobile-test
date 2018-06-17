package br.com.ifood.tweetmood.presentation.base;

import br.com.ifood.tweetmood.R;
import br.com.ifood.tweetmood.presentation.util.DialogUtils;
import dagger.android.support.DaggerAppCompatActivity;
import dagger.internal.Beta;

/**
 * Created by uchoa on 10/06/18.
 */
@Beta
public class BaseActivity extends DaggerAppCompatActivity {

    public void finishApplicationWithErrorMessage(int text){
        new DialogUtils().showDialog(this,
                text,
                R.string.action_ok,
                (dialogInterface, i) -> finish());
    }
}
