package com.zendesk.ticketviewer.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@lombok.Data
@JsonIgnoreProperties({"to", "from"})
public class Source {

    private String rel;

    public Source() {}

    @JsonCreator
    public Source(
            @JsonProperty("rel") String rel
    ) {
        this.rel = rel;
    }
}
