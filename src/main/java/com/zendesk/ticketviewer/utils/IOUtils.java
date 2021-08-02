package com.zendesk.ticketviewer.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zendesk.ticketviewer.models.Ticket;
import org.springframework.stereotype.Component;

@Component
public class IOUtils {

    public String prettifyJson(Object jsonObject) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            return "Prettification failed :(";
        }
    }

    public String toStringTicket(Ticket ticket) {
        StringBuilder sb = new StringBuilder()
                .append("Ticket Details for Ticket " + ticket.getId() + " \n")
                .append("\tSubject: " + (ticket.getSubject() == null ? "No Subject" : ticket.getSubject()) + "\n")
                .append("\tDescription: " + (ticket.getDescription() == null ? "No Description" : ticket.getDescription().replace("\n\n", "\n").replace("\n", "\n\t\t")) + "\n")
                .append("\tSubmitter: " + ticket.getSubmitterId() + "\n")
                .append("\tStatus: " + (ticket.getStatus() == null ? "No Status" : ticket.getStatus()) + "\n")
                .append("\tPriority: " + (ticket.getPriority() == null ? "Unknown" : ticket.getPriority()) + "\n");
        return sb.toString();
    }
}
