package com.zendesk.ticketviewer.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinksTest {

    private String validJson = "{" +
            "\"next\": \"nextUrl\"," +
            "\"prev\": \"prevUrl\"" +
            "}";

    private String invalidJson = "";

    private String invalidJson2 = "[]";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testParseableTo() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Links links = mapper.readerFor(Links.class).readValue(validJson);
            assertEquals("nextUrl", links.getNextUrl());
            assertEquals("prevUrl", links.getPrevUrl());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }


        assertThrows(JsonProcessingException.class, () -> mapper.readerFor(Links.class).readValue(invalidJson));
        assertThrows(JsonProcessingException.class, () -> mapper.readerFor(Links.class).readValue(invalidJson2));
    }
}