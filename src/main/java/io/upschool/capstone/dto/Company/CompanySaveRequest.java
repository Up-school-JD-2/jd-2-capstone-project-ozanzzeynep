package io.upschool.capstone.dto.Company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanySaveRequest {

    @NotBlank
    @Size(max = 100)
    private String companyName;
}
