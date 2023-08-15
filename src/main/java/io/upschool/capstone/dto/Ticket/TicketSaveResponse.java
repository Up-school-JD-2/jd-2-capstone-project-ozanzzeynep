package io.upschool.capstone.dto.Ticket;

import io.upschool.capstone.entity.Flight;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSaveResponse {

    private Long ticketId;
    private String flightCode;
    private String pasengerName;
    private String ticketNumber;
    private boolean active;
    private String message;


}
