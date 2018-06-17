package br.com.ifood.tweetmood.domain.model.Request;

import java.io.Serializable;

import br.com.ifood.tweetmood.domain.Consts;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by uchoa on 12/06/18.
 */

@Data
@AllArgsConstructor
public class AnalyzeSentimentRequest implements Serializable{


    private DocumentRequest document;
    private String encodingType;

    public AnalyzeSentimentRequest(String content) {
        this.document = new DocumentRequest(content);
        this.encodingType = Consts.GOOGLE_ENCODING_TYPE_UTF_8;

    }

}
