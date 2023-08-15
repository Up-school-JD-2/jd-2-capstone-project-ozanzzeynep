package io.upschool.capstone.dto.Flight;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class FlightSaveRequest {

    private String companyName;
    private String date;
    private String time;
    private String departureAirport;
    private String arrivalAirport;

}
