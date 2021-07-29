package com.zendesk.ticketviewer.service.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zendesk.ticketviewer.models.Ticket;
import com.zendesk.ticketviewer.models.TicketsResponse;
import com.zendesk.ticketviewer.service.TicketService;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Stack;

public class PaginatedResponse {

    TicketsResponse curPage;
    TicketsResponse nextPage;
    Stack<TicketsResponse> prev = new Stack<>();
    Stack<TicketsResponse> prevBacklog = new Stack<>();

    public PaginatedResponse(TicketsResponse firstPage) {
        nextPage = firstPage;
    }

    public TicketsResponse getNextPage() throws JsonProcessingException {
        if (nextPage == null) throw new IllegalStateException("You have reached the end of the pages!");
        if (nextPage.getMeta() == null) throw new IllegalStateException("You have reached the end of the pages!");
        if (!nextPage.getMeta().getHasMore()) throw new IllegalStateException("You have reached the end of the pages!");

        prev.push(curPage);
        curPage = nextPage;
        if (prevBacklog.isEmpty()) {
            nextPage = getNextPage(curPage);
        } else {
            nextPage = prevBacklog.pop();
        }
        return curPage;
    }

    public TicketsResponse getPrevPage() {
        if (prev.isEmpty()) throw new IllegalStateException("There is no previous page to navigate to!");

        prevBacklog.push(nextPage);
        nextPage = curPage;
        curPage = prev.pop();
        return curPage;
    }

    public List<TicketsResponse> getAllPages() {
        return null;
    }

    private TicketsResponse getNextPage(TicketsResponse prev) throws JsonProcessingException {
        if (prev == null) return null;
        if (prev.getMeta() == null) return null;
        if (!prev.getMeta().getHasMore()) return null;
        if (prev.getLinks() == null) return null;
        if (prev.getLinks().getNextUrl() == null) return null;
        return TicketService.fetchFromUrl(prev.getLinks().getNextUrl());
    }

}
