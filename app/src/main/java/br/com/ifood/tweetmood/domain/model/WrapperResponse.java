package br.com.ifood.tweetmood.domain.model;

import br.com.ifood.tweetmood.domain.model.Response.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by uchoa on 14/06/18.
 */

@Data
@AllArgsConstructor
public class WrapperResponse<T> {

    private T response;

    private ErrorResponse error;
}
