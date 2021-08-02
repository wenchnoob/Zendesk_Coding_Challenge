package com.zendesk.ticketviewer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zendesk.ticketviewer.models.Ticket;
import com.zendesk.ticketviewer.models.TicketResponse;
import com.zendesk.ticketviewer.models.TicketsResponse;
import com.zendesk.ticketviewer.services.models.PaginatedResponse;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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

    @Getter
    private static final String idEndpoint = "/api/v2/tickets/%d";

    private static WebClient client;

    @Autowired
    public TicketService(WebClient webClient) {
        this.client = webClient;
    }


    public PaginatedResponse fetchTickets() {
        return fetchTicketsWithPageSize(25);
    }

    public PaginatedResponse fetchTicketsWithPageSize(int size) {
        return new PaginatedResponse(fetchFromUrl(url + String.format(endpoint, size)));
    }

    public static TicketsResponse fetchFromUrl(String fetchUrl) {
        fetchUrl = URLDecoder.decode(fetchUrl, StandardCharsets.UTF_8);
        client.method(HttpMethod.GET);

        WebClient.RequestHeadersUriSpec spec = client.get();
        spec.uri(fetchUrl);
        spec.header("Authorization", "Basic " + Base64.encodeBase64String((email + ":" + password).getBytes(StandardCharsets.UTF_8)));
        spec.accept(MediaType.ALL);

        try {
            Mono<String> res = spec.retrieve().bodyToMono(String.class);

            String ugJson = res.block();

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            TicketsResponse ticketsResponse = mapper.readerFor(TicketsResponse.class).readValue(ugJson);

            return ticketsResponse;
        } catch (Exception e) {
            return TicketsResponse.FAIL;
        }
    }

    public Ticket getTicketById(long id) {
        String fetchUrl = URLDecoder.decode(url + String.format(idEndpoint, id), StandardCharsets.UTF_8);
        client.method(HttpMethod.GET);

        WebClient.RequestHeadersUriSpec spec = client.get();
        spec.uri(fetchUrl);
        spec.header("Authorization", "Basic " + Base64.encodeBase64String((email + ":" + password).getBytes(StandardCharsets.UTF_8)));
        spec.accept(MediaType.ALL);

        try {
            Mono<String> res = spec.retrieve().bodyToMono(String.class);

            String ugJson = res.block();

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            TicketResponse ticketResponse = mapper.readerFor(TicketResponse.class).readValue(ugJson);

            return ticketResponse != null ? ticketResponse.getTicket() : null;
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
