package io.upschool.capstone.dto.Airport;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirportSaveRequest {

    @NotBlank
    @Size(min = 3,max = 50)
    private String airportName;
}
