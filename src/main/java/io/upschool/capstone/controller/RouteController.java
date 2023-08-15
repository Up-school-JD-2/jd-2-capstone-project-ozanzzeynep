package io.upschool.capstone.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.upschool.capstone.dto.BaseResponse;
import io.upschool.capstone.dto.Flight.FlightSaveRequest;
import io.upschool.capstone.dto.Route.RouteSaveRequest;
import io.upschool.capstone.dto.Route.RouteSaveResponse;
import io.upschool.capstone.entity.Route;
import io.upschool.capstone.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/capstone/route")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping
    @Operation(
            tags = {"Route System"},
            summary = "add route"
    )
    public ResponseEntity<Object> createRoute(@RequestBody RouteSaveRequest request){
        var routeSaveResponse = routeService.save(request);
        var response = BaseResponse.<RouteSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(routeSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(
            tags = {"Route System"},
            summary = "search route"
    )
    public ResponseEntity<List<Route>> searchRoutues(@RequestBody String companyName) {
        return ResponseEntity.ok(routeService.searchRoutes(companyName));
    }
}
