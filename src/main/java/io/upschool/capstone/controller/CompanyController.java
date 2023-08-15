package io.upschool.capstone.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.upschool.capstone.dto.Airport.AirportSaveResponse;
import io.upschool.capstone.dto.BaseResponse;
import io.upschool.capstone.dto.Company.CompanySaveRequest;
import io.upschool.capstone.dto.Company.CompanySaveResponse;
import io.upschool.capstone.dto.Flight.FlightSaveRequest;
import io.upschool.capstone.entity.Company;
import io.upschool.capstone.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/capstone/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    @Operation(
            tags = {"Company System"},
            summary = "search company"
    )
    public ResponseEntity<Object> searchCompanies(@Param("Company Name : ") String companyName) {
        return ResponseEntity.ok(companyService.searchCompanies(companyName));
    }

    @PostMapping
    @Operation(
            tags = {"Company System"},
            summary = "add company"
    )
    public ResponseEntity<Object> createCompany(@Valid @RequestParam("Company Name : ") String companyName) {
        var companyRequest = CompanySaveRequest.builder().companyName(companyName).build();
        var companySaveResponse = companyService.save(companyRequest);
        var response = BaseResponse.<CompanySaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(companySaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
