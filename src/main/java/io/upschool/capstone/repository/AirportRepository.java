package io.upschool.capstone.repository;

import io.upschool.capstone.entity.Airport;
import io.upschool.capstone.entity.Route;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport,Long> {

    @Query("select a from Airport a where a.name like concat('%',:query, '%')")
    List<Airport> searchAirport(String query);

    @Query("select ra from Airport ra where ra.name = :query")
    Airport searchAirportByName(String query);

    @Query(value = "select count(a) from Airport a where a.name = :name")
    int findAirportCountByName(@Param("name") String name);
}
