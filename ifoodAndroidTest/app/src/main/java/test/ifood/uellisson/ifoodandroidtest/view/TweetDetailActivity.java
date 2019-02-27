package test.ifood.uellisson.ifoodandroidtest.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import test.ifood.uellisson.ifoodandroidtest.ConstantsUtil;
import test.ifood.uellisson.ifoodandroidtest.R;
import test.ifood.uellisson.ifoodandroidtest.presenter.AnalisePresenter;

public class TweetDetailActivity extends AppCompatActivity implements AnalisePresenter.View{

    @Bind(R.id.iv_emoji)
    ImageView IvEmoji;

    @Bind(R.id.tweet_message)
    TextView tweetMessage;

    @Bind(R.id.create_at)
    TextView createAt;
    private AnalisePresenter analisePresenter;
    private String tweet_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        tweetMessage.setText(extras.getString(ConstantsUtil.TWEET_MESSAGE));
        createAt.setText(extras.getString(ConstantsUtil.CREATE_AT));
        this.tweet_message = extras.getString(ConstantsUtil.TWEET_MESSAGE);

        analisePresenter = new AnalisePresenter(this);
        analisePresenter.prepareApi();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showSentiment(int score) {

    }
}
