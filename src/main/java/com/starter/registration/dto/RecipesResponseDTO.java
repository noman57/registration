package com.starter.registration.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecipesResponseDTO {

    private String link;

    private String title;

    private String body;

}
