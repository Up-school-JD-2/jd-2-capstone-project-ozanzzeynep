package io.upschool.capstone.service;

import io.upschool.capstone.dto.Flight.FlightSaveRequest;
import io.upschool.capstone.dto.Flight.FlightSaveResponse;
import io.upschool.capstone.dto.Flight.FlightSearchResponse;
import io.upschool.capstone.entity.Airport;
import io.upschool.capstone.entity.Company;
import io.upschool.capstone.entity.Flight;
import io.upschool.capstone.entity.Route;
import io.upschool.capstone.exception.FlightAlreadySavedException;
import io.upschool.capstone.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightService {

    private final FlightRepository flightRepository;
    private final CompanyService companyService;
    private final RouteService routeService;
    private final AirportService airportService;

    public Flight findFlightbyFlightCode(String flightCode) {
        return flightRepository.searchFlightByFlightCode(flightCode);
    }

    @Transactional
    public FlightSaveResponse save(FlightSaveRequest request) {
        checkIsFlightAlreadySaved(request);
        Company company = companyService.getCompanyIdByName(request.getCompanyName());
        Airport departureAirport = airportService.getAirportByName(request.getDepartureAirport());
        Airport arrivalAirport = airportService.getAirportByName(request.getArrivalAirport());
        Route route = Route.builder()
                .company(company)
                .departure_airport(departureAirport)
                .arrival_airport(arrivalAirport)
                .build();
        var newRoute = routeService.save(route);
        var newFlight = Flight.builder()
                .flightCode(company.getName().substring(0, 3).toUpperCase() + "-" + (int) (Math.random() * 9000))
                .company(company)
                .date(request.getDate())
                .time(request.getTime())
                .route(newRoute)
                .build();
        var flightResponse = flightRepository.save(newFlight);
        return FlightSaveResponse.builder()
                .flightCode(flightResponse.getFlightCode())
                .companyName(flightResponse.getCompany())
                .price(flightResponse.getPrice())
                .totalSeat(flightResponse.getTotalSeat())
                .date(flightResponse.getDate())
                .time(flightResponse.getTime())
                .departureAirport(newRoute.getDeparture_airport().getName())
                .arrivalAirport(newRoute.getArrival_airport().getName())
                .build();
    }

    @Transactional(readOnly = true)
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Transactional(readOnly = true)
    public FlightSearchResponse searchByFlightCode(String query) {
        var flightResponse = flightRepository.searchFlightByFlightCode(query);
        Company companyByReference = companyService.getReferenceById(flightResponse.getCompany().getId());
        Route routeByReference = routeService.getReferenceById(flightResponse.getRoute().getId());
        return FlightSearchResponse.builder()
                .id(flightResponse.getId())
                .companyName(companyByReference.getName())
                .date(flightResponse.getDate())
                .time(flightResponse.getTime())
                .departureAirport(routeByReference.getDeparture_airport().getName())
                .arrivalAirport(routeByReference.getArrival_airport().getName())
                .build();
    }

    @Transactional(readOnly = true)
    public Flight searchFlightByCode(String query) {
        return flightRepository.searchFlightByFlightCode(query);
    }

    @Transactional(readOnly = true)
    public List<Flight> searchByDate(String query) {
        return flightRepository.searchByDate(query);
    }

    private void checkIsFlightAlreadySaved(FlightSaveRequest request) {
        int flightCount = flightRepository.findFlightCount(request.getDepartureAirport());
        if (flightCount > 0) {
            throw new FlightAlreadySavedException("Flight already saved.");
        }
    }

}

