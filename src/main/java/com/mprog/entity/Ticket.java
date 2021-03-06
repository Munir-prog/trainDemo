package com.mprog.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Ticket {
    int id;
    String passengerNo;
    String passengerName;
    String passengerLastName;
    int routeId;
    int railwayCarNo;
    String seatNo;
    int cost;
}
