//package org.example.anikudasaikodik.providers;
//
//
//import org.example.anikudasaikodik.dto.kodikDTO.KodikAnimeDTO;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.util.*;
//
//@Component
//public class KodikProvider implements AnimeProvider {
//
//    @Value("${kodik.url}")
//    private String kodikUrl;
//
//    @Value("${kodik.token}")
//    private String kodikToken;
//
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    @Override
//    public Optional<KodikAnimeDTO> findById(String id) {
//        String url = UriComponentsBuilder
//                .fromHttpUrl(kodikUrl + "/search")  // kodikUrl берется из application.properties
//                .queryParam("token", kodikToken)   // kodikToken тоже из пропертей
//                .queryParam("shikimori_id", id)
//                .queryParam("with_episodes", "true")
//                .toUriString();
//
//        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
//        List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");
//
//        if (results == null || results.isEmpty()) return Optional.empty();
//
//        return Optional.of(KodikAnimeDTO.fromMap(results.get(0)));
//    }
//
//
//    @Override
//    public List<KodikAnimeDTO> search(String query) {
//    String url = UriComponentsBuilder.fromHttpUrl(kodikUrl + "/search")
//            .queryParam("token", kodikToken)
//            .queryParam("title", query)
//            .toUriString();
//
//    Map<String, Object> response = restTemplate.getForObject(url, Map.class);
//    List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");
//
//    if (results == null) return Collections.emptyList();
//
//    List<KodikAnimeDTO> list = new ArrayList<>();
//    for (Map<String, Object> r : results) list.add(KodikAnimeDTO.fromMap(r));
//
//    return list;
//    }
//
//    @Override
//    public List<KodikAnimeDTO> getList(int page, int limit) {
//        String url = UriComponentsBuilder.fromHttpUrl(kodikUrl + "/list")
//            .queryParam("token", kodikToken)
//            .queryParam("types", "anime,anime-serial")
//            .queryParam("limit", limit)
//            .queryParam("page", page)
//            .toUriString();
//        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
//        List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");
//
//        if (results == null) return Collections.emptyList();
//
//        List<KodikAnimeDTO> list = new ArrayList<>();
//        for (Map<String, Object> r : results) list.add(KodikAnimeDTO.fromMap(r));
//
//        return list;
//        }
//    }
