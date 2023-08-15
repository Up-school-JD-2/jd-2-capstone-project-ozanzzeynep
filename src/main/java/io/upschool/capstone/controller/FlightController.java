package io.upschool.capstone.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.upschool.capstone.dto.BaseResponse;
import io.upschool.capstone.dto.Flight.FlightSaveRequest;
import io.upschool.capstone.dto.Flight.FlightSaveResponse;
import io.upschool.capstone.entity.Flight;
import io.upschool.capstone.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/capstone/flight")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/code")
    @Operation(
            tags = {"Flight System"},
            summary = "search flight by code"
    )
    public ResponseEntity<Object> searchByFLightCode(@RequestParam("Flight Code : ") String flightCode) {
        return ResponseEntity.ok(flightService.searchByFlightCode(flightCode));
    }

    @GetMapping("/date")
    @Operation(
            tags = {"Flight System"},
            summary = "search flight by date"
    )
    public ResponseEntity<List<Flight>> searchByDate(@RequestParam("Flight Date (dd/MM/yyyy) : ") String flightDate) {
        return ResponseEntity.ok(flightService.searchByDate(flightDate));
    }

    @PostMapping
    @Operation(
            tags = {"Flight System"},
            summary = "add flight"
    )
    public ResponseEntity<Object> createFlight(@Valid @RequestBody FlightSaveRequest request){
        var flightSaveResponse = flightService.save(request);
        var response = BaseResponse.<FlightSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(flightSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
