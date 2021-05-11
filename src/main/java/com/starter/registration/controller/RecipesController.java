package com.starter.registration.controller;

import com.starter.registration.dto.RecipesResponseDTO;
import com.starter.registration.dto.UserInfoDTO;
import com.starter.registration.service.ScrapperService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/recipes")
@AllArgsConstructor
public class RecipesController {

    private ScrapperService scrapperService;

    @GetMapping
    public ResponseEntity<List<RecipesResponseDTO>> getUserInfo(HttpServletRequest request) throws IOException {
        return ResponseEntity.ok(scrapperService.getData());
    }

}
