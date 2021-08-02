package com.zendesk.ticketviewer.services;

import com.zendesk.ticketviewer.models.*;
import com.zendesk.ticketviewer.services.models.PaginatedResponse;
import com.zendesk.ticketviewer.utils.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {


    @InjectMocks
    TicketService ticketService;

    @Mock
    WebClient client;

    @Mock
    WebClient.RequestHeadersUriSpec spec;

    @Mock
    WebClient.ResponseSpec resSpec;

    IOUtils ioUtils = new IOUtils();

    TicketsResponse fake = new TicketsResponse(
            Arrays.asList(
                    new Ticket(),
                    new Ticket(),
                    new Ticket()
            ),
            new Meta(false, null, null),
            new Links(null, null)
    );

    PaginatedResponse fakeTicketsResponse = new PaginatedResponse(fake);

    Ticket fakeTicket = new Ticket();
    Supplier<TicketResponse> fakeTicketResponse = () -> {
        fakeTicket.setId(10);
        return new TicketResponse (
                fakeTicket
        );
    };

    @BeforeEach
    void setUp() {
        when(client.get()).thenReturn(spec);
        when(spec.retrieve()).thenReturn(resSpec);

    }

    @Test
    void fetchTickets() {
        when(resSpec.bodyToMono(String.class)).thenReturn(Mono.just(ioUtils.prettifyJson(fake)));
        PaginatedResponse pr = ticketService.fetchTickets();
        String expected = ioUtils.prettifyJson(fakeTicketsResponse);
        String actual = ioUtils.prettifyJson(pr);
        assertEquals(expected, actual);
    }

    @Test
    void getTicketById() {
        fakeTicket.setId(10);
        when(resSpec.bodyToMono(String.class)).thenReturn(Mono.just(ioUtils.prettifyJson(fakeTicketResponse.get())));
        Ticket pr = ticketService.getTicketById(10);
        assertEquals(fakeTicket, pr);
    }
}