package io.upschool.capstone.dto.Flight;

import io.upschool.capstone.entity.Airport;
import io.upschool.capstone.entity.Company;
import io.upschool.capstone.entity.Route;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightSaveResponse {

    private String flightCode;
    private Company companyName;
    private double price;
    private int totalSeat;
    private String  date;
    private String time;
    private String departureAirport;
    private String arrivalAirport;
}
