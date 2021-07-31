package com.zendesk.ticketviewer.services.models;

import com.zendesk.ticketviewer.models.TicketsResponse;
import com.zendesk.ticketviewer.services.TicketService;

public class PaginatedResponse {

    TicketsResponse curPage;

    public PaginatedResponse(TicketsResponse firstPage) {
        curPage = firstPage;
    }

    public boolean hasPage() {
        return curPage != null;
    }

    private boolean verify(TicketsResponse curPage) {
        if (curPage == null) return false;
        if (curPage.getMeta() == null) return false;
        if (!curPage.getMeta().getHasMore()) return false;
        if (curPage.getLinks() == null) return false;
        return true;
    }

    public TicketsResponse getPrevPage() {
        if (!verify(curPage)) curPage =  null;
        else if (curPage.getLinks().getPrevUrl() == null) curPage = null;
        else curPage = TicketService.fetchFromUrl(curPage.getLinks().getPrevUrl());
        return curPage;
    }

    public TicketsResponse getCurPage() {
        return curPage;
    }


    public TicketsResponse getNextPage() {
        if (!verify(curPage)) curPage = null;
        else if (curPage.getLinks().getNextUrl() == null) curPage = null;
        else curPage = TicketService.fetchFromUrl(curPage.getLinks().getNextUrl());
        return curPage;
    }

}
