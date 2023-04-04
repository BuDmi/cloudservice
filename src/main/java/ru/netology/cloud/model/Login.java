package ru.netology.cloud.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Login {
    @NotBlank
    @JsonProperty("auth-token")
    private String authToken;
}
