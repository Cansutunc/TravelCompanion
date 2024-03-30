package com.travelcompanion.TravelCompanion.RabbitMQ;

import java.io.Serializable;

import com.travelcompanion.TravelCompanion.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomMessage(@JsonProperty("message") User user) implements Serializable {

    public User getUser() {
        return user;
    }

}
