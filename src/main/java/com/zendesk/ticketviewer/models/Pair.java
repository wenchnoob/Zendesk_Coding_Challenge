package com.zendesk.ticketviewer.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@lombok.Data
public class Pair<K, V>{
    K key;
    V val;

    public Pair() {}

    @JsonCreator
    public Pair(
            @JsonProperty("id") K key,
            @JsonProperty("value") V val
    ) {
        this.key = key;
        this.val = val;
    }
}
