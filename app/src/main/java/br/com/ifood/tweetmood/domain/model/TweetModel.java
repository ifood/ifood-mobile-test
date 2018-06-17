package br.com.ifood.tweetmood.domain.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by uchoa on 10/06/18.
 */

@Data
@AllArgsConstructor
@Builder
public class TweetModel implements Serializable{

    private String text;
    private String name;
    private String screenName;
    private String urlProfile;

}
