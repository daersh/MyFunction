package com.daersh.daersh_project.notion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class NotionPageListResponse {

    private List<NotionPage> results;
    private String next_cursor;
    private boolean has_more;

    // 생성자, getter, setter 생략


    @Setter
    @Getter
    public static class NotionPage {
        private String id;
        private String created_time;
        private String last_edited_time;
        private String url;
        private Properties properties;

        // 생성자, getter, setter 생략

    }

    @Setter
    @Getter
    public static class Properties {
        @JsonProperty("Name")
        private TitleProperty name;

        // 생성자, getter, setter 생략

    }

    @Setter
    @Getter
    public static class TitleProperty {
        private List<TextContent> title;

        // 생성자, getter, setter 생략

    }

    @Setter
    @Getter
    public static class TextContent {
        private String content;
        // 생성자, getter, setter 생략
    }
}
