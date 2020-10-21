package com.kzk.kcodegen.app;

import com.google.common.collect.Maps;
import com.kzk.kcodegen.core.KCodeGenerator;
import com.kzk.kcodegen.model.ApiDocs;
import com.kzk.kcodegen.tools.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author kazeki
 */
@Slf4j
public class HelloGenerator {

    public static final String TEMPLATE_ID_HELLO = "hello";

    private KCodeGenerator kCodeGenerator;

    public HelloGenerator(KCodeGenerator kCodeGenerator){
        this.kCodeGenerator = kCodeGenerator;
    }

    public void render(String outputDir) {
        Map<String, Object> datas = Maps.newHashMap();
        datas.put("name", "kazeki");
        datas.put("age", 12);
        datas.put("msg", "hello");
        try {
            kCodeGenerator.render(TEMPLATE_ID_HELLO, datas, outputDir + "/hello.txt", true);
        } catch (Exception e) {
            log.error("异常!", e);
        }
    }

}
