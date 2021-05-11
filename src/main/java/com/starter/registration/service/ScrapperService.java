package com.starter.registration.service;

import com.starter.registration.dto.RecipesResponseDTO;

import java.io.IOException;
import java.util.List;

public interface ScrapperService {

    List<RecipesResponseDTO> getData() throws IOException;
}
