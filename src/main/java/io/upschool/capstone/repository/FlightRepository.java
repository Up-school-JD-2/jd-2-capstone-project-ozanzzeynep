package io.upschool.capstone.repository;

import io.upschool.capstone.entity.Flight;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface FlightRepository extends JpaRepository<Flight,Long> {

    @Query("select f from Flight f where f.flightCode = :query")
    Flight searchFlightByFlightCode(String query);

    @Query("select fd from Flight fd where fd.date = :query")
    List<Flight> searchByDate(String query);

    @Query(value = "select count(fe) from Flight fe where fe.route.departure_airport.name = :departureAirport")
    int findFlightCount(@Param("departureAirport") String departureAirport);

}
