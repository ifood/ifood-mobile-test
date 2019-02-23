package test.ifood.uellisson.ifoodandroidtest.view;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.ifood.uellisson.ifoodandroidtest.R;
import test.ifood.uellisson.ifoodandroidtest.model.Tweet;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.twitter_username)
    EditText twitterUserName;

    @Bind(R.id.list_tweets)
    RecyclerView listTweets;

    private TweetAdapter tweetAdapter;
    LinearLayoutManager layoutManager;
    DividerItemDecoration itemDecor;

    List<Tweet> lt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick (R.id.search_button)
    public void showTwitter (){
        Tweet t1 = new Tweet("mensagem 1", "hoje");
        Tweet t2 = new Tweet("mensagem 2", "ontem");
        Tweet t3 = new Tweet("mensagem 3", "quinta");
        lt = new ArrayList<>();
        lt.add(t1);
        lt.add(t2);
        lt.add(t3);
        tweetAdapter = new TweetAdapter(this, lt);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        listTweets.setLayoutManager(layoutManager);

        itemDecor = new DividerItemDecoration(getApplicationContext(), layoutManager.getOrientation());
        itemDecor.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.horizontal_line));

        listTweets.addItemDecoration(itemDecor);
        listTweets.setAdapter(tweetAdapter);
    }
}
