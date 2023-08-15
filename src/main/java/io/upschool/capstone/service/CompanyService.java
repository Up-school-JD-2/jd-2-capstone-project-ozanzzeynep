package io.upschool.capstone.service;

import io.upschool.capstone.dto.Company.CompanySaveRequest;
import io.upschool.capstone.dto.Company.CompanySaveResponse;
import io.upschool.capstone.entity.Company;
import io.upschool.capstone.exception.CompanyAlreadySavedException;
import io.upschool.capstone.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanySaveResponse save(CompanySaveRequest companySaveRequest){
        checkIsAirportAlreadySaved(companySaveRequest);
        var newCompany = Company.builder().name(companySaveRequest.getCompanyName()).build();
        Company savedCompany = companyRepository.save(newCompany);
        return CompanySaveResponse
                .builder()
                .companyName(savedCompany.getName())
                .companyId(savedCompany.getId())
                .build();
    }
    @Transactional(readOnly = true)
    public Company getReferenceById(Long id){
        return companyRepository.getReferenceById(id);
    }


    public List<Company> searchCompanies(String query){
        List<Company> companies = companyRepository.searchCompany(query);
        return companies;
    }

    @Transactional(readOnly = true)
    public Company getCompanyIdByName(String query){
        return companyRepository.findCompanyByName(query);
    }

    private void checkIsAirportAlreadySaved(CompanySaveRequest request){
        int companyCountByName = companyRepository.findAirportCountByName(request.getCompanyName());
        if(companyCountByName > 0){
            throw new CompanyAlreadySavedException("Company already saved");
        }
    }
}
