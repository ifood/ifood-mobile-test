package test.ifood.uellisson.ifoodandroidtest.data.mapper;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import test.ifood.uellisson.ifoodandroidtest.data.model.TweetEntity;
import test.ifood.uellisson.ifoodandroidtest.model.Tweet;

public class TweetMapper {

    private List<TweetEntity> tweetEntityList;

    public TweetMapper(List<TweetEntity> tweetEntityList) {
        this.tweetEntityList = tweetEntityList;
    }

    public List<Tweet> transformTweetEntity () {
        List<Tweet> tweetListMaped = new ArrayList<>();

        for (TweetEntity tweetEntity : tweetEntityList) {
            Tweet tweet = new Tweet(tweetEntity.getText(), convertTwitterDate(tweetEntity.getCreatedAt()));
            tweetListMaped.add(tweet);
        }

        return tweetListMaped;
    }

    private String convertTwitterDate(Object dateTwitter) {
        if (dateTwitter != null) {
            Date date;
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.US);
            SimpleDateFormat dateFormatTwitter = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH);
            dateFormatTwitter.setLenient(true);

            try {
                date = dateFormatTwitter.parse((String) dateTwitter);
                return sdf.format(date);
            } catch (ParseException e) {
                Log.e("Error", "Parser Error "+ e.getMessage());
                e.printStackTrace();
            }
        }

        return null;
    }
}
