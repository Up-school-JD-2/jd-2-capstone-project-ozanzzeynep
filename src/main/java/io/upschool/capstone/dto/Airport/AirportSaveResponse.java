package io.upschool.capstone.dto.Airport;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportSaveResponse {

    private Long airportId;
    private String airportName;
}
