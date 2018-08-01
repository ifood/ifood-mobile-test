package com.rafamatias.nlp.domain.mapper;

import com.rafamatias.nlp.data.entity.SentimentResponse;
import com.rafamatias.nlp.presentation.model.SentimentModel;

public class SentimentMapper {

    public static SentimentModel fromSentiment(SentimentResponse sentiment) {
        return new SentimentModel(sentiment.getScore());
    }

}
