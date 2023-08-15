package io.upschool.capstone.dto.Ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSaveRequest {


    @NotBlank
    private String flightCode;
    @NotBlank
    @Size(min = 3, max = 50)
    private String passengerName;
    @NotBlank
    private String cardNo;
    @NotBlank
    private String expirationDate;
    @NotBlank
    private String cvv;

}
