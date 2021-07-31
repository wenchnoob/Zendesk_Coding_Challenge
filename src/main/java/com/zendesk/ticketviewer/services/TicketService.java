package com.zendesk.ticketviewer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zendesk.ticketviewer.models.Ticket;
import com.zendesk.ticketviewer.models.TicketsResponse;
import com.zendesk.ticketviewer.services.models.PaginatedResponse;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TicketService {

    @Getter
    @Setter
    private static String url;

    @Getter
    @Setter
    private static String email;

    @Getter
    @Setter
    private static String password;

    @Getter
    private static final String endpoint = "/api/v2/tickets?page[size]=%d";

    public PaginatedResponse fetchTickets() {
        return fetchTicketsWithPageSize(25);
    }

    public PaginatedResponse fetchTicketsWithPageSize(int size) {
        return new PaginatedResponse(fetchFromUrl(url + String.format(endpoint, size)));
    }

    public static TicketsResponse fetchFromUrl(String fetchUrl) {
        fetchUrl = URLDecoder.decode(fetchUrl, StandardCharsets.UTF_8);
        WebClient client = WebClient
                .builder()
                .baseUrl(fetchUrl)
                .build();

        client.method(HttpMethod.GET);

        WebClient.RequestHeadersUriSpec spec = client.get();
        spec.header("Authorization", "Basic " + Base64.encodeBase64String((email + ":" + password).getBytes(StandardCharsets.UTF_8)));
        spec.accept(MediaType.ALL);

        try {
            Mono<String> res = spec.retrieve().bodyToMono(String.class);

            String ugJson = res.block();

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            TicketsResponse ticketsResponse = mapper.readerFor(TicketsResponse.class).readValue(ugJson);

            return ticketsResponse;
        } catch (WebClientResponseException | JsonProcessingException e) {
            return TicketsResponse.FAIL;
        }
    }

    public List<Ticket> getAllTickets() {
        PaginatedResponse res = fetchTickets();
        List<Ticket> tickets = new ArrayList<>();

        TicketsResponse cur;
        while(res.hasPage()) {
            cur = res.getCurPage();
            tickets.addAll(cur.getTickets());
            res.getNextPage();
        }

        return tickets;
    }

    public Ticket getTicketById(long id) {
        try {
            return getAllTickets()
                    .stream()
                    .parallel()
                    .filter(ticket -> ticket.getId() == id)
                    .collect(Collectors.toList())
                    .get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Value("${zendesk.api.baseurl}")
    public void setUrl(String baseurl) {
        url = baseurl;
    }

    @Value("${zendesk.api.auth.email}")
    public void setEmail(String pEmail) {
        email = pEmail;
    }

    @Value("${zendesk.api.auth.password}")
    public void setPassword(String pPassword) {
        password = pPassword;
    }
}
