package test.ifood.uellisson.ifoodandroidtest.presenter;

import android.app.Activity;
import android.util.Log;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.language.v1.CloudNaturalLanguage;
import com.google.api.services.language.v1.CloudNaturalLanguageRequest;
import com.google.api.services.language.v1.CloudNaturalLanguageScopes;
import com.google.api.services.language.v1.model.AnalyzeSentimentRequest;
import com.google.api.services.language.v1.model.AnalyzeSentimentResponse;
import com.google.api.services.language.v1.model.Document;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import test.ifood.uellisson.ifoodandroidtest.ConstantsUtil;
import test.ifood.uellisson.ifoodandroidtest.data.model.SentimentInfo;
import test.ifood.uellisson.ifoodandroidtest.data.network.AccessTokenGoogleNL;

public class AnalisePresenter extends Presenter<AnalisePresenter.View> {

    private GoogleCredential mCredential;
    private Thread mThread;
    private final BlockingQueue<CloudNaturalLanguageRequest<? extends GenericJson>> mRequests
            = new ArrayBlockingQueue<>(3);
    private String tweet_message;
    private Activity activity;

    private CloudNaturalLanguage mApi = new CloudNaturalLanguage.Builder(
            new NetHttpTransport(),
            JacksonFactory.getDefaultInstance(),
            new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest request) throws IOException {
                    mCredential.initialize(request);
                }
            }).build();


    public AnalisePresenter(Activity activity, String tweet_message) {
        this.activity = activity;
        this.tweet_message = tweet_message;
    }

    public interface View extends Presenter.BaseView {
        void showSentiment (ConstantsUtil.SENTIMENT sentiment);
    }

    public void prepareApi() {
        getView().showLoading();
        new AccessTokenGoogleNL(activity, new AccessTokenGoogleNL.PostTaskListener<String>() {
            @Override
            public void onPostTask(String result) {
                setAccessToken(result);
                analyzeSentiment(tweet_message);
            }
        }).execute();
    }

    public void setAccessToken(String token) {
        mCredential = new GoogleCredential()
                .setAccessToken(token)
                .createScoped(CloudNaturalLanguageScopes.all());
        startWorkerThread();
    }

    private void startWorkerThread() {
        if (mThread != null) {
            return;
        }
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (mThread == null) {
                        break;
                    }
                    try {
                        deliverResponse(mRequests.take().execute());
                    } catch (InterruptedException e) {
                        Log.e("Error", "Interrupted.", e);
                        getView().showErrorMessage(ConstantsUtil.ANALISE_ERROR);
                        getView().hideLoading();
                        break;
                    } catch (IOException e) {
                        getView().showErrorMessage(ConstantsUtil.ANALISE_ERROR);
                        getView().hideLoading();
                        Log.e("Error", "Failed to execute a request.", e);
                    }
                }
            }
        });
        mThread.start();
    }

    public void analyzeSentiment(String text) {
        try {
            mRequests.add(mApi
                    .documents()
                    .analyzeSentiment(new AnalyzeSentimentRequest()
                            .setDocument(new Document()
                                    .setContent(text)
                                    .setType("PLAIN_TEXT"))));
        } catch (IOException e) {
            Log.e("Error", "Failed to create analyze request.", e);
        }
    }

    private void deliverResponse(GenericJson response) {
        final SentimentInfo sentiment = new SentimentInfo(((AnalyzeSentimentResponse) response)
                .getDocumentSentiment());
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getView().showSentiment(getSentiment(sentiment.score));
                getView().hideLoading();
            }
        });
    }

    public ConstantsUtil.SENTIMENT getSentiment(float score) {
        if (score > 0.25) {
            return ConstantsUtil.SENTIMENT.HAPPY;
        } else if (score == 0.25) {
            return ConstantsUtil.SENTIMENT.NEUTRAL;
        } else {
            return ConstantsUtil.SENTIMENT.SAD;
        }
    }
}
