package com.zendesk.ticketviewer.services;

import com.zendesk.ticketviewer.models.*;
import com.zendesk.ticketviewer.services.models.PaginatedResponse;
import com.zendesk.ticketviewer.utils.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {com.zendesk.ticketviewer.services.TicketService.class,
                                com.zendesk.ticketviewer.TicketViewerApplication.class},
loader = AnnotationConfigContextLoader.class)
public class TicketServiceTest {


    @Autowired
    TicketService ticketService;

    @BeforeAll
    static void setProperties() {
        // Replace this with your properties
        System.setProperty("zendesk.api.baseurl","https://zccnewwenchy.zendesk.com");
        System.setProperty("zendesk.api.auth.email", "wcdutreuil@gmail.com");
        System.setProperty("zendesk.api.auth.password", "*******");
    }

    @Test
    void fetchTickets() {
        PaginatedResponse pr = ticketService.fetchTickets();
        List<Ticket> tickets = pr.getCurPage().getTickets();
        assertTrue(tickets != null);
    }

    @Test
    void fetchTicketsWithPageSize() {
        PaginatedResponse pr = ticketService.fetchTicketsWithPageSize(30);
        List<Ticket> tickets = pr.getCurPage().getTickets();
        assertTrue(tickets != null && tickets.size() <= 30);
    }

    @Test
    void getTicketById() {
        Ticket ticket = ticketService.getTicketById(12);
        assertTrue(ticket == null || ticket.getId() == 12);
    }


}