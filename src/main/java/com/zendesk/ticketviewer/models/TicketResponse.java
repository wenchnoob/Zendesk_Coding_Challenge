package com.zendesk.ticketviewer.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Getter
@lombok.Setter
public class TicketResponse {

    private Ticket ticket;

    @JsonCreator
    public TicketResponse(@JsonProperty("ticket") Ticket ticket) {
        this.ticket = ticket;
    }
}
