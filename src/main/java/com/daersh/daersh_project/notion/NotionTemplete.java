package com.daersh.daersh_project.notion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@RestController
@RequestMapping("/getNotion")
public class NotionTemplete {

    @Value("${notion.api}")
    private String key;

    @GetMapping("")
    public ResponseEntity<NotionPageListResponse> getNotionDB(){
        RestTemplate notionTemplete = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", key);
        headers.set("Notion-Version", "2022-06-28");
        headers.set("Content-Type", "application/json");
        String url = "https://api.notion.com/v1/databases/955d569c8de74458a4c06e2fa664b902/query";
        HttpEntity<String> request = new HttpEntity<>(headers);
        try {
            ResponseEntity<NotionPageListResponse> response = notionTemplete.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    NotionPageListResponse.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                NotionPageListResponse body = response.getBody();
                if (body != null) {
                    System.out.println("Results: " + body.getResults().size());
                    for (NotionPageListResponse.NotionPage page : body.getResults()) {
                        System.out.println("Page ID: " + page.getId());
                        System.out.println("Page Title: " + page.getProperties().getName().getTitle().get(0).getContent());
                        System.out.println("Page URL: " + page.getUrl());
                        System.out.println("Created Time: " + page.getCreated_time());
                        System.out.println("Last Edited Time: " + page.getLast_edited_time());
                        System.out.println();
                    }
                }
                return ResponseEntity.ok(body);
            } else {
                System.err.println("Request failed with status code: " + response.getStatusCode());
                return ResponseEntity.status(response.getStatusCode()).build();
            }
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
