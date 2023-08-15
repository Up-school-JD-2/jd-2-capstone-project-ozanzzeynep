package io.upschool.capstone.dto.Route;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSaveRequest {

    private String companyName;
    private String departureAirport;
    private String arrivalAirport;
}
