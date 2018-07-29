package com.rafamatias.nlp.presentation.view.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.rafamatias.nlp.R;
import com.rafamatias.nlp.databinding.ActivityTweetsBinding;
import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.presentation.model.TweetModel;
import com.rafamatias.nlp.presentation.view.adapter.TweetsAdapter;
import com.rafamatias.nlp.presentation.view.fragment.TweetFragment;
import com.rafamatias.nlp.presentation.viewModel.TweetsViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link AppCompatActivity} subclass.
 * Uses {@link ViewPager} to show fragments of {@link TweetFragment}
 * @see TweetsAdapter
 * @see TweetFragment
 */
public class TweetsActivity extends AppCompatActivity {

    private ActivityTweetsBinding binding;
    private TweetsAdapter viewPagerAdapter;
    private List<TweetModel> viewPagerData = new ArrayList<>();
    private TweetsViewModel viewModel;

    private EditText usernameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tweets);

        usernameText = new EditText(this);

        setupViewModel();
        setupViewPager();
    }

    public void actionRetry(View view){
        viewModel.getTweets();
    }

    public void onChangeUsername(View view){
        alertDialogBuilder().create().show();
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(TweetsViewModel.class);
        viewModel.getTweets().observe(this, getTweetsObserver());
        viewModel.getUsername().observe(this, getUsernameObserver());
    }

    private void setupViewPager() {
        viewPagerAdapter = new TweetsAdapter(getSupportFragmentManager(), viewPagerData);
        binding.viewPager.setAdapter(viewPagerAdapter);
    }

    private Observer<String> getUsernameObserver(){
        return new Observer<String>() {
            @Override
            public void onChanged(@Nullable String resource) {
                binding.textName.setText(getString(R.string.text_username, resource));
            }
        };
    }

    private Observer<Resource<List<TweetModel>>> getTweetsObserver() {
        return new Observer<Resource<List<TweetModel>>>() {
            @Override
            public void onChanged(Resource<List<TweetModel>> resource) {
                switch (resource.state){
                    case ERROR:
                        showError();
                        break;
                    case LOADING:
                        showLoading();
                        break;
                    default:
                        showTweets(resource.data);
                        break;
                }
            }
        };
    }

    public AlertDialog.Builder alertDialogBuilder(){
        LayoutInflater li = LayoutInflater.from(this);
        final View promptView = li.inflate(R.layout.dialog_prompt, null);
        final EditText usernameText = promptView.findViewById(R.id.textUsername);

        return new AlertDialog.Builder(this)
                .setTitle(R.string.title_dialog_change_username)
                .setView(promptView)
                .setPositiveButton(R.string.abc_change, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.getTweets(usernameText.getText().toString());
                    }
                });
    }

    private void showLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.viewPager.setVisibility(View.GONE);
        binding.containerRetry.setVisibility(View.GONE);
    }

    private void showTweets(List<TweetModel> tweets){
        binding.containerRetry.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);
        binding.viewPager.setVisibility(View.VISIBLE);

        tweets.get(0).createdAt();

        viewPagerData.clear();
        viewPagerData.addAll(tweets);
        viewPagerAdapter.notifyDataSetChanged();
    }

    private void showError(){
        binding.containerRetry.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
        binding.viewPager.setVisibility(View.GONE);
    }

}
