package com.zendesk.ticketviewer.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@lombok.Data
public class Ticket {

    public Ticket() {}

    @JsonCreator
    public Ticket(@JsonProperty("allow_attachments") boolean allowAttachments) {

    }
}
