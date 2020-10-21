package com.kzk.kcodegen.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author kazeki
 */
@Data
public class ApiDocs {
    private String swagger;
    private Info info;
    private Map<String, Map<String, Path>> paths;
    private Map<String, Definition> definitions;

    @Data
    public static class Info{
        private String description;
        private String version;
        private String title;
    }

    @Data
    public static class Path{
        private String summary;
        private String operationId;
        private List<Parameter> parameters;
        private Map<Integer, Response> responses;

        @Data
        public static class Parameter{
            private String name;
            private String in;
            private String description;
            private Boolean required;
            private String type;
            private String format;
            private Schema schema;
            private String defaultValue;
        }

        @Data
        public static class Response{
            private Schema schema;
            private String description;
        }

    }

    @Data
    public static class Definition {
        private String title;
        private String description;
        private String type;
        private List<String> required;
        private Map<String, Schema> properties;
    }

    @Data
    public static class Schema {
        @JsonProperty("$ref")
        private String ref;
        private String type;
        private String format;
        private Schema items;
    }
}
