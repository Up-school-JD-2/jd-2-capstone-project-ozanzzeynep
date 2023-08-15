package io.upschool.capstone.service;

import io.upschool.capstone.dto.Airport.AirportSaveRequest;
import io.upschool.capstone.dto.Airport.AirportSaveResponse;
import io.upschool.capstone.entity.Airport;
import io.upschool.capstone.exception.AirportAlreadySavedException;
import io.upschool.capstone.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AirportService {

    private final AirportRepository airportRepository;

    @Transactional
    public AirportSaveResponse save(AirportSaveRequest airportSaveRequest) {
        checkIsAirportAlreadySaved(airportSaveRequest);
        var newAirport = Airport.builder().name(airportSaveRequest.getAirportName()).build();
        Airport savedAirport = airportRepository.save(newAirport);
        return AirportSaveResponse
                .builder()
                .airportName(savedAirport.getName())
                .airportId(savedAirport.getId())
                .build();
    }
    @Transactional(readOnly = true)
    public Airport getReferenceById(Long id) {
        return airportRepository.getReferenceById(id);
    }

    public List<Airport> searchAirports(String airportname) {
        List<Airport> airports = airportRepository.searchAirport(airportname);
        return airports;
    }

    @Transactional(readOnly = true)
    public Airport getAirportByName(String airportName) {
        return airportRepository.searchAirportByName(airportName);
    }


    private void checkIsAirportAlreadySaved(AirportSaveRequest request){
        int airportCountByName = airportRepository.findAirportCountByName(request.getAirportName());
        if(airportCountByName > 0){
            throw new AirportAlreadySavedException("Airport already saved.");
        }
    }
}
