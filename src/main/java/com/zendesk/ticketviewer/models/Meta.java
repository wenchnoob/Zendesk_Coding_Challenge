package com.zendesk.ticketviewer.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@lombok.Data
public class Meta {

    private Boolean hasMore;
    private String afterCursor;
    private String beforeCursor;

    public Meta() {}

    @JsonCreator
    public Meta(
            @JsonProperty("has_more") boolean hasMore,
            @JsonProperty("after_cursor") String afterCursor,
            @JsonProperty("before_cursor") String beforeCursor
    ) {
        this.hasMore = hasMore;
        this.afterCursor = afterCursor;
        this.beforeCursor = beforeCursor;
    }
}
