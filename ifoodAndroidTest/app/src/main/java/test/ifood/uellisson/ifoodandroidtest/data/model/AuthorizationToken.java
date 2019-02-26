package test.ifood.uellisson.ifoodandroidtest.data.model;

import com.google.gson.annotations.SerializedName;

public class AuthorizationToken {
    @SerializedName("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
