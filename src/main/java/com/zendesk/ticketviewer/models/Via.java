package com.zendesk.ticketviewer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@lombok.Data
public class Via {

    private String channel;
    private Source source;

    public Via() {}

    public Via(
            @JsonProperty("channel") String channel,
            @JsonProperty("source") Source source
    ) {
        this.channel = channel;
        this.source = source;
    }
}
