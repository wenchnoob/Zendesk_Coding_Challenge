package com.zendesk.ticketviewer.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@lombok.Data
public class SatisfactionRating {

    private String comment;
    private long id;
    private String score;

    public SatisfactionRating() {}

    @JsonCreator
    public SatisfactionRating(
            @JsonProperty("comment") String comment,
            @JsonProperty("id") long id,
            @JsonProperty("score") String score
    ) {
        this.comment = comment;
        this.id = id;
        this.score = score;
    }
}
