package io.upschool.capstone.service;

import io.upschool.capstone.dto.Route.RouteSaveRequest;
import io.upschool.capstone.dto.Route.RouteSaveResponse;
import io.upschool.capstone.entity.Airport;
import io.upschool.capstone.entity.Company;
import io.upschool.capstone.entity.Route;
import io.upschool.capstone.exception.RouteAlreadySavedException;
import io.upschool.capstone.repository.CompanyRepository;
import io.upschool.capstone.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RouteService {

    private final RouteRepository routeRepository;
    private final AirportService airportService;
    private final CompanyRepository companyRepository;

    @Transactional
    public RouteSaveResponse save(RouteSaveRequest request) {
        checkIsRouteAlreadySaved(request);
        Company company = companyRepository.findCompanyByName(request.getCompanyName());
        Airport departureAirportByReference = airportService.getAirportByName(request.getDepartureAirport());
        Airport arrivalAirportByReference = airportService.getAirportByName(request.getArrivalAirport());
        var newRoute = Route.builder()
                .company(company)
                .departure_airport(departureAirportByReference)
                .arrival_airport(arrivalAirportByReference)
                .build();
        var routeResponse = routeRepository.save(newRoute);
        return RouteSaveResponse.builder()
                .routeId(routeResponse.getId())
                .companyName(company.getName())
                .departureAirport(routeResponse.getDeparture_airport().getName())
                .arrivalAirport(routeResponse.getArrival_airport().getName())
                .build();
    }

    public Route save(Route route) {
        return routeRepository.save(route);
    }

    public Route getReferenceById(Long id) {
        Route route = routeRepository.getReferenceById(id);
        return route;
    }

    public Route getRouteByName(Airport departureAirport, Airport arrivalAirport) {
        return routeRepository.findAirportByName(departureAirport, arrivalAirport);
    }

    public List<Route> searchRoutes(String companyName) {
        Company companyResponse = companyRepository.findCompanyByName(companyName);
        return routeRepository.searchRoutesByCompany(companyResponse.getId());
    }

    private void checkIsRouteAlreadySaved(RouteSaveRequest request) {
        int routeCount = routeRepository.findRoureCountByDepartureAirport(request.getDepartureAirport());
        if (routeCount > 0) {
            throw new RouteAlreadySavedException("Route already saved");
        }
    }
}
