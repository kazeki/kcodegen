package com.kzk.kcodegen.tools;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

/**
 * @author kazeki
 */
@Slf4j
public class WebTool {
    public static String get(String url) throws URISyntaxException, IOException {
        HttpGet httpGet = new HttpGet();
        httpGet.setURI(new URI(url));
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse res = httpClient.execute(httpGet);
        if (res.getStatusLine().getStatusCode() == 200) {
            InputStream is = res.getEntity().getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String content = String.join("", br.lines().collect(Collectors.toList()));
            log.info(content);
            return content;
        } else {
            throw new RuntimeException("Http Get ERROR (status code: {" + res.getStatusLine().getStatusCode() + "})");
        }
    }
}
