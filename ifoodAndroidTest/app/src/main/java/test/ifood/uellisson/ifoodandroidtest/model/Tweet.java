package test.ifood.uellisson.ifoodandroidtest.model;

public class Tweet {

    private String TweetMessage;
    private String createAt;

    public Tweet(String TweetMessage, String createAt) {
        this.TweetMessage = TweetMessage;
        this.createAt = createAt;
    }

    public String getTwitterMessage() {
        return TweetMessage;
    }

    public String getCreateAt() {
        return createAt;
    }
}
