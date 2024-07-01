package com.daersh.daersh_project.notion;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
public class NotionPageListResponse {
    private List<NotionPage> results;
    private String next_cursor;
    private boolean has_more;
    @Setter
    @Getter
    public static class NotionPage {
        private String id;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date created_time;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date last_edited_time;
        private String url;
        private Properties properties;
    }

    @Setter
    @Getter
    public static class Properties {
        @JsonProperty("Date")
        private DateProperty date;
        @JsonProperty("Name")
        private TitleProperty name;
    }

    @Getter
    @Setter
    public static class DateProperty{
        private ProjectDate date;
    }

    @Getter
    @Setter
    public static class ProjectDate{
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date start;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date end;
    }

    @Setter
    @Getter
    public static class TitleProperty {
        private List<TextContent> title;
    }

    @Setter
    @Getter
    public static class TextContent {
        private Text text;
    }
    @Setter
    @Getter
    public static class Text{
        private String content;
    }
}
