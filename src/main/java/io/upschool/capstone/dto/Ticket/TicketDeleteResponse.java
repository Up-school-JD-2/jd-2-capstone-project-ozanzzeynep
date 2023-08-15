package io.upschool.capstone.dto.Ticket;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDeleteResponse {

    private Long id;
    private boolean active;
}
