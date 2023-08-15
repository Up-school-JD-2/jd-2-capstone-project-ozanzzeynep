package io.upschool.capstone.dto.Route;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSaveResponse {

    private Long routeId;
    private String companyName;
    private String departureAirport;
    private String arrivalAirport;

}
