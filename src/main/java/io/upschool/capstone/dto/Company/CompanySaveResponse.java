package io.upschool.capstone.dto.Company;


import io.upschool.capstone.entity.Flight;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanySaveResponse {

    private Long companyId;
    private String companyName;

}
