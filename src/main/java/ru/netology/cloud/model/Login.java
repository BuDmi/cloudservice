package ru.netology.cloud.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class Login {
    @NotBlank
    @JsonProperty("auth-token")
    private String authToken;
}
