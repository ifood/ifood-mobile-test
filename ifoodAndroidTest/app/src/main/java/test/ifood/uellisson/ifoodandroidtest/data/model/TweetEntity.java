package test.ifood.uellisson.ifoodandroidtest.data.model;

import com.google.gson.annotations.SerializedName;

public class TweetEntity {
    @SerializedName("created_at") private Object createdAt;
    @SerializedName("text") private String text;

    public Object getCreatedAt() {
        return createdAt;
    }

    public String getText() {
        return text;
    }
}
