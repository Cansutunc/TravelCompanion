package com.energyxchange.EnergyXChange.RabbitMQ;

import java.io.Serializable;

import com.energyxchange.EnergyXChange.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomMessage(@JsonProperty("message") User user) implements Serializable {

    public User getUser() {
        return user;
    }

}
