package io.upschool.capstone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse <T>{

    private int status;

    @JsonProperty("isSuccess")
    private boolean isSuccess;

    private String error = "no messagge available";

    private T data;
}
