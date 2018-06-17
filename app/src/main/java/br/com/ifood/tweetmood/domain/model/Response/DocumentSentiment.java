package br.com.ifood.tweetmood.domain.model.Response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by uchoa on 12/06/18.
 */

@Data
@AllArgsConstructor
public class DocumentSentiment implements Serializable{
    private Number magnitude;
    private Number score;
}
