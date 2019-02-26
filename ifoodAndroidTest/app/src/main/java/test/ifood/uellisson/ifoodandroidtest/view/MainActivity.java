package test.ifood.uellisson.ifoodandroidtest.view;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.ifood.uellisson.ifoodandroidtest.R;
import test.ifood.uellisson.ifoodandroidtest.model.Tweet;
import test.ifood.uellisson.ifoodandroidtest.presenter.TweetsPresenter;

public class MainActivity extends AppCompatActivity implements TweetsPresenter.View {

    @Bind(R.id.twitter_username)
    EditText twitterUserName;

    @Bind(R.id.list_tweets)
    RecyclerView listTweets;

    @Bind(R.id.loading)
    ProgressBar loading;

    private TweetAdapter tweetAdapter;
    LinearLayoutManager layoutManager;
    DividerItemDecoration itemDecor;
    TweetsPresenter tweetsPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick (R.id.search_button)
    public void searchTwittes(){
        tweetsPresenter = new TweetsPresenter();
        tweetsPresenter.setView(this);
        if (twitterUserName.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(R.string.empty_userName), Toast.LENGTH_LONG).show();
        } else {
            tweetsPresenter.initialize(twitterUserName.getText().toString().replace(" ", ""));
        }
    }

    @Override
    public void showTwittes(List<Tweet> tweetList) {
        tweetAdapter = new TweetAdapter(this, tweetList);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        listTweets.setLayoutManager(layoutManager);

        itemDecor = new DividerItemDecoration(getApplicationContext(), layoutManager.getOrientation());
        itemDecor.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.horizontal_line));
        listTweets.addItemDecoration(itemDecor);

        if (tweetList == null) {
            listTweets.setAdapter(null);
        } else {
            listTweets.setAdapter(tweetAdapter);
        }
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
