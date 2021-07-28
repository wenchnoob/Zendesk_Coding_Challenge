package com.zendesk.ticketviewer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@lombok.Data
public class TicketsResponse {

    private List<Ticket> tickets;
    private Meta meta;
    private Links links;

    public TicketsResponse() {}

    public TicketsResponse(
            @JsonProperty("tickets") List<Ticket> tickets,
            @JsonProperty("meta") Meta meta,
            @JsonProperty("links") Links links
    ) {
        this.tickets = tickets;
        this.meta = meta;
        this.links = links;
    }
}
