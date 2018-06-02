package com.yke.twittershrink.Search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yke.twittershrink.R;
import com.yke.twittershrink.Twitter.Tweet;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchInterface {

    private SearchAdapter adapter = new SearchAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        Intent intent = getIntent();
        String userSearchName = intent.getStringExtra("User");

        //RecyclerView:
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = findViewById(R.id.recView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        //Set ActionBar Title:
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("@" + userSearchName);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Search Implementation:
        SearchImplementation searchImp = new SearchImplementation(this);
        searchImp.onSearch(userSearchName);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void setItems(List<Tweet> tweets) {
        if (tweets.size() > 0) {
            adapter.setTweets(tweets);
        } else {
            Toast.makeText(this, "User Not Found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void dismissLoading() {
        ProgressBar progressBar = findViewById(R.id.searchProgressBar);
        progressBar.setVisibility(View.GONE);
    }
}
