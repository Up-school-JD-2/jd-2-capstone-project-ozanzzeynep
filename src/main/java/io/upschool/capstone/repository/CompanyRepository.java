package io.upschool.capstone.repository;

import io.upschool.capstone.entity.Company;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {


    @Query("select c from Company c where c.name like concat('%',:query, '%') ")
    List<Company> searchCompany(String query);

    @Query("select  fc from Company fc where fc.name = :query")
    Company findCompanyByName(String query);

    @Query(value = "select count(c) from Company c where c.name = :name")
    int findAirportCountByName(@Param("name")String companyName);
}
