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
public class DocumentRequest implements Serializable{
    private String type;
    private String content;

    public DocumentRequest(String content){
        this.type = Consts.GOOGLE_TEXT_TYPE_PLAIN_TEXT;
        this.content = content;
    }

}
