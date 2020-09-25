package com.kzk.kcodegen;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Map;

/**
 * @author kazeki
 */
@Slf4j
public class App {
    private App() {
    }

    public static void main(String[] args) {
        try {
            String serviceName = "bigdata-service";
            String rootPackage = "com.kzk.bigdata.api";
            KCodeGenerator kcg = new KCodeGenerator();
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            ApiDocs apiDocs = om.readValue(new URL("http://192.168.5.61:7090/v2/api-docs"), ApiDocs.class);
            ApiDocs apiDocs = om.readValue(new File("D:/test/apidocs.json"), ApiDocs.class);
            Map<String, Map<String, ApiDocs.Path>> paths = apiDocs.getPaths();
            kcg.reanderFeignClient(serviceName, rootPackage, apiDocs);
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
