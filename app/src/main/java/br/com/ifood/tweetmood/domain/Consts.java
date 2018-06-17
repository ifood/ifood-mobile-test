package br.com.ifood.tweetmood.domain;

/**
 * Created by uchoa on 14/06/18.
 */

public class Consts {

    /**
     * Google constants
     * */
    public static final String GOOGLE_ENCODING_TYPE_UTF_8 = "UTF8";
    public static final String GOOGLE_TEXT_TYPE_PLAIN_TEXT = "PLAIN_TEXT";


    /**
     * Bundle
     * */
    public static final String BUNDLE_TWEET_SEARCH_SUMMARY = "tweet_search_summary";
    public final static String BUNDLE_ANALYSES_SENTIMENT = "analyze_sentiment";
    public final static String BUNDLE_TWEET = "tweet";


    /**
     * Exception
     * */
    public final static String SEARCH_TO_TWEET_SUMMARY_DATA_EXCEPTION = "Error converting search to tweet summary data.";
    public final static String SEARCH_TO_TWEET_METADATA_EXCEPTION = "Error converting search to tweet metadata.";


    /**
     * Error
     */
    public final static String LOG_ERROR = "service_error";
    public static final String LOG_TRANSLATOR_ERROR = "translator_error";

    public final static String TWITTER_REQUEST_START ="twitter_request_start";
    public final static String TWITTER_REQUEST_FINISH ="twitter_request_finish";
    public static final String GOOGLE_REQUEST_START = "google_request_start";
    public static final String GOOGLE_REQUEST_FINISH = "google_request_finish";
}