package io.upschool.capstone.dto.Ticket;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSearchResponse {

    private String company;
    private String departureAirport;
    private String arrivalAirport;
    private String date;
    private String time;
    private String passengerName;
    private double ticketPrice;
    private boolean active;
}
