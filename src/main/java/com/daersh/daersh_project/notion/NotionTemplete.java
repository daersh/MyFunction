package com.daersh.daersh_project.notion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
    public void getNotionDB(){
        RestTemplate notionTemplete = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", key);
        headers.set("Notion-Version", "2022-06-28");
        headers.set("Content-Type", "application/json");
        String url = "https://api.notion.com/v1/databases/955d569c8de74458a4c06e2fa664b902/query";
        HttpEntity<String> request = new HttpEntity<>(headers);
        Map result1 = notionTemplete.postForObject(
                url,
                request,
                Map.class
        );
        System.out.println(result1);
    }
}
