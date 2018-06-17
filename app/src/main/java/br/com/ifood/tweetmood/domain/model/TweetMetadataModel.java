package br.com.ifood.tweetmood.domain.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by uchoa on 12/06/18.
 */

@Builder
@AllArgsConstructor
@Data
public class TweetMetadataModel implements Serializable{
    public final String name;
    public final String username;
    public final String url;
    public final long maxId;
    public final long sinceId;
    public final String refreshUrl;
    public final String nextResults;
    public final long count;
    public final double completedIn;
    public final String sinceIdStr;
    public final String query;
    public final String maxIdStr;
}
