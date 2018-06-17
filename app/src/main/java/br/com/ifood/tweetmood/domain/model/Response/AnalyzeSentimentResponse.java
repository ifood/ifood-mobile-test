package br.com.ifood.tweetmood.domain.model.Response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by uchoa on 12/06/18.
 */

@Builder
@Data
@AllArgsConstructor
public class AnalyzeSentimentResponse implements Serializable{
    private DocumentSentiment documentSentiment;
}
