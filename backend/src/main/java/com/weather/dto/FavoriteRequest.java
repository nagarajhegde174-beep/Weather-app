package com.weather.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteRequest {

    @NotBlank(message = "City name cannot be blank")
    @Size(max = 100, message = "City name cannot exceed 100 characters")
    private String city;
}
