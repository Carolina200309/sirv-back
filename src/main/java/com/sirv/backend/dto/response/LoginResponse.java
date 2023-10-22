package com.sirv.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;
}
