package com.starter.registration.service;

import com.starter.registration.dto.RecipesResponseDTO;
import com.starter.registration.dto.UserCreateDTO;
import com.starter.registration.entity.User;
import com.starter.registration.repository.UserRepository;
import com.starter.registration.service.impl.ScrapperServiceImpl;
import com.starter.registration.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScrapperServiceImplTest {

    private ScrapperService scrapperService;

    @BeforeEach
    void initUseCase() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        scrapperService = new ScrapperServiceImpl();
    }

    @Test
    void createNewUser() throws IOException {
        List<RecipesResponseDTO> data = scrapperService.getData();
        assertThat(data).hasSize(16);
    }
}
