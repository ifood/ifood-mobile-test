package test.ifood.uellisson.ifoodandroidtest.view;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import test.ifood.uellisson.ifoodandroidtest.ConstantsUtil;
import test.ifood.uellisson.ifoodandroidtest.R;
import test.ifood.uellisson.ifoodandroidtest.presenter.AnalisePresenter;

public class TweetDetailActivity extends AppCompatActivity implements AnalisePresenter.View{

    @Bind(R.id.bg_detail)
    RelativeLayout backgrond;

    @Bind(R.id.iv_emoji)
    ImageView ivEmoji;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

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

        analisePresenter = new AnalisePresenter(this, tweet_message);
        analisePresenter.setView(this);
        analisePresenter.prepareApi();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSentiment(ConstantsUtil.SENTIMENT sentiment) {
        ivEmoji.setVisibility(View.VISIBLE);
         if (sentiment.equals(ConstantsUtil.SENTIMENT.HAPPY)) {
             backgrond.setBackgroundColor(ContextCompat.getColor(this, R.color.colorHappy));
             ivEmoji.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.emoji_happy));
         } else if (sentiment.equals(ConstantsUtil.SENTIMENT.NEUTRAL)) {
             backgrond.setBackgroundColor(ContextCompat.getColor(this, R.color.colorNeutral));
             ivEmoji.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.emoji_neutral));
         } else {
             backgrond.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSad));
             ivEmoji.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.emoji_sad));
         }
    }
}
