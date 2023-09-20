package com.example.comidasapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ComidaRecordDto(

        @NotBlank @NotNull String title,
        String company,
        @NotNull @NotBlank String price,
         String direction,
        @NotBlank @Size String phone,
        @NotBlank String image,
        String link
) {

}
