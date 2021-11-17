package com.example;

import com.amazon.ask.model.interfaces.alexa.comms.messagingcontroller.StatusMap;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class CatFact {

    private String text;


    public CatFact(String text){

        this.text = text;
    }

    public CatFact(){};

    public String getText() {
        return text;
    }
}
