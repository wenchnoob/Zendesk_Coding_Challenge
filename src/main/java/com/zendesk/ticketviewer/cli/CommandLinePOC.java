package com.zendesk.ticketviewer.cli;

import com.zendesk.ticketviewer.service.TicketService;
import com.zendesk.ticketviewer.service.models.PaginatedResponse;
import com.zendesk.ticketviewer.utils.CLIUtils;
import com.zendesk.ticketviewer.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

@Component
public class CommandLinePOC implements CommandLineRunner {


    @Autowired
    TicketService ticketService;

    @Autowired
    IOUtils ioUtils;

    @Autowired
    CLIUtils cliUtils;

    @Override
    public void run(String... args) throws Exception {
        PaginatedResponse pr = ticketService.fetchTickets();
        cliUtils.initializeCLI(TicketService.getEndpoint());
        /*TicketsResponse firstPage = pr.getNextPage();
        //System.out.println(ioUtils.toStringTicket(firstPage.getTickets().get(0)));
        TicketsResponse secondPage = pr.getNextPage();
        TicketsResponse thirdPage = pr.getNextPage();
        TicketsResponse fourthPage = pr.getNextPage();
        pr.getPrevPage();
        pr.getPrevPage();
        TicketsResponse back = pr.getPrevPage();
        pr.getNextPage();
        pr.getNextPage();
        TicketsResponse forward = pr.getNextPage();

        System.out.println("First page: ");
        firstPage.getTickets().forEach(
                ticket -> {
                    System.out.print(ticket.getId() + ", ");
                }
        );
        System.out.println();

        System.out.println("Second page: ");
        secondPage.getTickets().forEach(
                ticket -> {
                    System.out.print(ticket.getId() + ", ");
                }
        );
        System.out.println();

        System.out.println("Third page: ");
        thirdPage.getTickets().forEach(
                ticket -> {
                    System.out.print(ticket.getId() + ", ");
                }
        );
        System.out.println();

        System.out.println("Fourth page: ");
        fourthPage.getTickets().forEach(
                ticket -> {
                    System.out.print(ticket.getId() + ", ");
                }
        );
        System.out.println();

        System.out.println("Back page: ");
        back.getTickets().forEach(
                ticket -> {
                    System.out.print(ticket.getId() + ", ");
                }
        );
        System.out.println();

        System.out.println("Forward page: ");
        forward.getTickets().forEach(
                ticket -> {
                    System.out.print(ticket.getId() + ", ");
                }
        );
        System.out.println();
*/
    }

}
