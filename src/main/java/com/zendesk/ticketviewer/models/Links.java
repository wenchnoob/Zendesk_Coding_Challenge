package com.zendesk.ticketviewer.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@lombok.Data
public class Links {

    private String nextUrl;
    private String prevUrl;

    public Links() {}

    @JsonCreator
    public Links(
            @JsonProperty("next") String nextUrl,
            @JsonProperty("prev") String prevUrl
    ) {
        this.nextUrl = nextUrl;
        this.prevUrl = prevUrl;
    }
}
