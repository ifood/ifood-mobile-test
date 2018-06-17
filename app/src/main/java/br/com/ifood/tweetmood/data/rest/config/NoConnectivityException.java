package br.com.ifood.tweetmood.data.rest.config;

import java.io.IOException;


/**
 * Created by uchoa on 10/06/18.
 */

public class NoConnectivityException extends IOException {

    private static final String NO_CONNECTIVITY_EXCEPTION_MESSAGE = "Error connecting to the internet. Verify you connection and try again.";

    @Override
    public String getMessage(){
        return NO_CONNECTIVITY_EXCEPTION_MESSAGE;
    }
}
