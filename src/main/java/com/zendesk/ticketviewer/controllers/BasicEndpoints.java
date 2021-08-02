package com.zendesk.ticketviewer.controllers;

import com.zendesk.ticketviewer.models.Ticket;
import com.zendesk.ticketviewer.models.TicketsResponse;
import com.zendesk.ticketviewer.services.TicketService;
import com.zendesk.ticketviewer.services.models.PaginatedResponse;
import com.zendesk.ticketviewer.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Controller
public class BasicEndpoints {

    @Autowired
    TicketService ticketService;

    @Autowired
    IOUtils ioUtils;

    PaginatedResponse pr;

    @RequestMapping("/")
    public String home() {
        return "../static/index.html";
    }

    @RequestMapping("/myError")
    public String error() {
        return "../static/error.html";
    }

    @RequestMapping("/tickets")
    public ModelAndView getTickets(@RequestParam(value = "domain", required = false) String domain,
                                   @RequestParam(value = "email", required = false) String email,
                                   @RequestParam(value = "password", required = false) String password) {
        if (domain != null && domain.length() >= 1) ticketService.setUrl(domain);
        if (email != null && domain.length() >= 1) ticketService.setEmail(email);
        if (password != null && domain.length() >= 1) ticketService.setPassword(password);

        pr = ticketService.fetchTickets();
        return getTicketsPage();
    }

    @RequestMapping("/tickets/{id}")
    public ModelAndView getTicket(@PathVariable("id") long id) {
        String viewName = "ticket";
        Map<String, Object> model = new Hashtable<>();
        Ticket ticket = ticketService.getTicketById(id);
        model.put("ticket", ticket);
        model.put("raw", ioUtils.prettifyJson(ticket));
        return new ModelAndView(viewName, model);
    }


    @RequestMapping("/tickets/next")
    public ModelAndView getNextPage() {
        if (pr == null) pr = ticketService.fetchTickets();
        else pr.getNextPage();
        return  getTicketsPage();
    }

    @RequestMapping("/tickets/cur")
    public ModelAndView getCurPage() {
        if (pr == null) pr = ticketService.fetchTickets();
        return  getTicketsPage();
    }

    @RequestMapping("/tickets/prev")
    public ModelAndView getPrevPage()  {
        if (pr == null) pr = ticketService.fetchTickets();
        else pr.getPrevPage();
        return  getTicketsPage();
    }

    public ModelAndView getTicketsPage() {
        TicketsResponse response = pr.getCurPage();

        Map<String, Object> model = new Hashtable<>();

        if (response == null || response.getTickets() == null ||
        response.getTickets().size() <= 0) {
            return new ModelAndView("redirect:/myError", model);
        }

        List<Ticket> tickets = response.getTickets();


        String viewName = "allTickets";

        model.put("tickets", tickets);

        return new ModelAndView(viewName, model);
    }
}
