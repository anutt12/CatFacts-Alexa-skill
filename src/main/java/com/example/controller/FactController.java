package com.example.controller;

import com.example.service.OpenAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


@Controller
public class FactController {

    private static final String template = "Your fact of the day is %s!";
    private final Logger logger = LoggerFactory.getLogger(FactController.class);


    @ResponseBody
    public String sayCatFact(@RequestParam(name = "text", required = false, defaultValue = "Cats are cool!") String text) throws IOException {

        logger.info("Meow");
        OpenAPIService openAPIService = new OpenAPIService();

        text = openAPIService.getApiRequest();

        return String.format(template, text);
    }
}
