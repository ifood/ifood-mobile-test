package br.com.ifood.tweetmood.presentation.view.getuser;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import br.com.ifood.tweetmood.R;
import br.com.ifood.tweetmood.presentation.base.BaseActivity;
import br.com.ifood.tweetmood.presentation.base.BaseActivityView;
import br.com.ifood.tweetmood.presentation.util.ActivityUtils;

public class GetUserTweetsActivity extends BaseActivity implements BaseActivityView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_get_user);

        new ActivityUtils().addFragmentToActivity(getSupportFragmentManager(), GetUserTweetsFragment.newInstance(), R.id.containerGetUser);
    }



    @Override
    public void replaceFragment(Fragment fragment) {
        new ActivityUtils().replaceFragment(getSupportFragmentManager(), fragment, R.id.containerGetUser);
    }

}
