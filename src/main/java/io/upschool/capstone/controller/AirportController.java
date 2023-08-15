package io.upschool.capstone.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.upschool.capstone.dto.Airport.AirportSaveRequest;
import io.upschool.capstone.dto.Airport.AirportSaveResponse;
import io.upschool.capstone.dto.BaseResponse;
import io.upschool.capstone.entity.Airport;
import io.upschool.capstone.service.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/capstone/airport")
@RequiredArgsConstructor

public class AirportController {

    private final AirportService airportService;

    @GetMapping
    @Operation(
            tags = {"Airport System"},
            summary = "search airport"
    )
    public ResponseEntity<Object> searchAirports(@Param("Airport Name : ") String airportName) {
        return ResponseEntity.ok(airportService.searchAirports(airportName));
    }

    @PostMapping
    @Operation(
            tags = {"Airport System"},
            summary = "add airport"
    )
    public ResponseEntity<Object> createAirport(@Valid
                                                @RequestParam("Airport Name : ") String airportName) {
        var airportRequest = AirportSaveRequest.builder().airportName(airportName).build();
        var airportSaveResponse = airportService.save(airportRequest);
        var response = BaseResponse.<AirportSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(airportSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
