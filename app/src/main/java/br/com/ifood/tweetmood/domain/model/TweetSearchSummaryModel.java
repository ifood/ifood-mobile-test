package br.com.ifood.tweetmood.domain.model;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by uchoa on 12/06/18.
 */
@Data
@Builder
@AllArgsConstructor
public class TweetSearchSummaryModel implements Serializable{
    private TweetMetadataModel metadata;
    private ArrayList<TweetModel> tweets;
}
