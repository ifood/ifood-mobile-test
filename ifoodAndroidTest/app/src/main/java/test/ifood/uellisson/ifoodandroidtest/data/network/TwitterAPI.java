package test.ifood.uellisson.ifoodandroidtest.data.network;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import test.ifood.uellisson.ifoodandroidtest.data.model.TwitterAuthorizationToken;
import test.ifood.uellisson.ifoodandroidtest.data.model.TweetEntity;

public interface TwitterAPI {

    @POST("oauth2/token")
    @FormUrlEncoded
    Call<TwitterAuthorizationToken> getAccessToken(@Header("Authorization") String authorization,
                                                   @Field("grant_type") String grantType);


    @GET("1.1/statuses/user_timeline.json")
    Call<List<TweetEntity>> getTweetsByUsername(
            @Header("Authorization") String authorization,
            @Query("screen_name") String query
    );
}
