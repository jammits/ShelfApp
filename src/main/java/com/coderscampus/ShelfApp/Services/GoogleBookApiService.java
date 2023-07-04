package com.coderscampus.ShelfApp.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class GoogleBookApiService {

    @Value("${google.relative.baseUrl}")
    private String baseUrl;

    @Value("${google.book.search}")
    private String bookSearch;

    @Value("${google.api.key}")
    private String apiKey;

    public <T> ResponseEntity<T> getResponse(String searchTerm, Class<T> responseClass) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl+bookSearch)
                .queryParam("q", searchTerm)
                .queryParam("key", apiKey)
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(uri, responseClass);
    }

}
