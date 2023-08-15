package io.upschool.capstone.repository;

import io.upschool.capstone.entity.Airport;
import io.upschool.capstone.entity.Company;
import io.upschool.capstone.entity.Route;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {

    @Query("select r from Route r where r.company.id= :query")
    List<Route> searchRoutesByCompany(@Param("query") Long id);

    @Query("select fd from Route fd where fd.departure_airport = :departureAirport and " +
            "fd.arrival_airport = :arrivalAirport")
    Route findAirportByName(Airport departureAirport,Airport arrivalAirport);

    @Query("select count(rd) from Route rd where rd.departure_airport.name= :departureAirport")
    int findRoureCountByDepartureAirport(@Param("departureAirport") String departureAirport);


}
