package com.camunda.bpm.sbexpensereportdemo.util;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class ApplicationHelper {

    public static URI buildURI(String url, String path){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).path(path);
        URI uri = builder.build().encode().toUri();
        return uri;
    }
}
