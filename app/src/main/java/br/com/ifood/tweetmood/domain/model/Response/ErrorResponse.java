package br.com.ifood.tweetmood.domain.model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by uchoa on 10/06/18.
 */

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;

}
