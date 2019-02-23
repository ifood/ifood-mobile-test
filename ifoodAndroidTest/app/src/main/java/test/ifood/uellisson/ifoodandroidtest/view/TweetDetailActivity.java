package test.ifood.uellisson.ifoodandroidtest.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import test.ifood.uellisson.ifoodandroidtest.ConstantsUtil;
import test.ifood.uellisson.ifoodandroidtest.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TweetDetailActivity extends AppCompatActivity {

    @Bind(R.id.iv_emoji)
    ImageView IvEmoji;

    @Bind(R.id.tweet_message)
    TextView tweetMessage;

    @Bind(R.id.create_at)
    TextView createAt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        tweetMessage.setText(extras.getString(ConstantsUtil.TWEET_MESSAGE));
        createAt.setText(extras.getString(ConstantsUtil.CREATE_AT));
    }
}
