package io.upschool.capstone.dto.Flight;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightSearchResponse {

    private Long id;
    private String companyName;
    private String date;
    private String time;
    private String departureAirport;
    private String arrivalAirport;
}
