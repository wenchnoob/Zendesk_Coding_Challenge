package com.zendesk.ticketviewer.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class CommandLinePOC implements CommandLineRunner {

    @Value("${zendesk.api.endpoint}")
    private String url;

    @Value("${zendesk.api.auth.email}")
    private String email;

    @Value("${zendesk.api.auth.password}")
    private String password;

    private final String endpoint = "/api/v2/tickets?page[size]=1";

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running...");
        WebClient client = WebClient
                .builder()
                .baseUrl(url + endpoint)
                .build();
        client.method(HttpMethod.GET);
        WebClient.RequestHeadersUriSpec spec = client.get();
        spec.header("Authorization", "Basic " + Base64.encodeBase64String((email + ":" + password).getBytes(StandardCharsets.UTF_8)));
        spec.accept(MediaType.ALL);
        Mono<String> res = spec.retrieve().bodyToMono(String.class);
        String ugJson = res.block();
        ObjectMapper mapper = new ObjectMapper();
        String pretJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ugJson);
        System.out.println(pretJson);
    }

}
