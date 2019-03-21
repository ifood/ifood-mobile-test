package test.ifood.uellisson.ifoodandroidtest.data.network;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.language.v1.CloudNaturalLanguageScopes;

import java.io.IOException;
import java.io.InputStream;

import test.ifood.uellisson.ifoodandroidtest.R;

public class AccessTokenGoogleNL extends AsyncTask<Void, Void, String> {

    private static final String TAG = "AccessTokenGoogleNL";
    private static final String PREFS = "AccessTokenGNL";
    private static final String PREF_ACCESS_TOKEN = "access_token";
    private Context context;
    private PostTaskListener<String> postTaskListener;

    public AccessTokenGoogleNL(Context context, PostTaskListener<String> postTaskListener) {
        this.context = context;
        this.postTaskListener = postTaskListener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        final SharedPreferences prefs =
                context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        String currentToken = prefs.getString(PREF_ACCESS_TOKEN, null);

        if (currentToken != null) {
            final GoogleCredential credential = new GoogleCredential()
                    .setAccessToken(currentToken)
                    .createScoped(CloudNaturalLanguageScopes.all());
            final Long seconds = credential.getExpiresInSeconds();
            if (seconds != null && seconds > 3600) {
                return currentToken;
            }
        }

        final InputStream stream = context.getResources().openRawResource(R.raw.credential);
        try {
            final GoogleCredential credential = GoogleCredential.fromStream(stream)
                    .createScoped(CloudNaturalLanguageScopes.all());
            credential.refreshToken();
            final String accessToken = credential.getAccessToken();
            prefs.edit().putString(PREF_ACCESS_TOKEN, accessToken).apply();
            return accessToken;
        } catch (IOException e) {
            Log.e(TAG, "Failed to obtain access token.", e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        postTaskListener.onPostTask(s);
    }

    public interface PostTaskListener<K> {
        void onPostTask(K result);
    }
}
