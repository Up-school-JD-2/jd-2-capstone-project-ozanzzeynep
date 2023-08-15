package io.upschool.capstone.dto.Ticket;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDeleteRequest {

    @NotBlank
    private String ticketNumber;
}
