package com.starter.registration.service.impl;

import com.starter.registration.dto.RecipesResponseDTO;
import com.starter.registration.service.ScrapperService;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScrapperServiceImpl implements ScrapperService {
    @Override
    public List<RecipesResponseDTO> getData() throws IOException {

        Document document = Jsoup
                .connect("https://www.spoonforkbacon.com/category/asian-recipes/")
                .get();
        Elements elementsByClass =document.getElementsByTag("article");
        return elementsByClass.stream().map(element -> {
            Elements elements = element.getElementsByClass("entry-title-link");
            String url = elements.get(0).attr("href");
            String header = elements.get(0).childNode(0).toString();
            Elements content = element.getElementsByClass("entry-content");
            return RecipesResponseDTO.builder().body(content.html()).link(url).title(header).build();

        }).collect(Collectors.toList());
    }
}
