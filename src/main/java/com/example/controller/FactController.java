package com.example.controller;

import com.example.service.OpenAPIService;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.io.IOException;


@Controller
public class FactController {

    private static final String template = "Your fact of the day is %s!";
    private final Logger logger = LoggerFactory.getLogger(FactController.class);


    public String sayCatFact() throws IOException, JSONException {

        logger.info("Meow");
        OpenAPIService openAPIService = new OpenAPIService();

        String text = openAPIService.getApiRequest();

        return String.format(template, text);
    }
}
