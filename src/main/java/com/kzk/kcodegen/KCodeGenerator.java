package com.kzk.kcodegen;

import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author kazeki
 */
@Slf4j
public class KCodeGenerator {
    private static VelocityEngine ve;

    public KCodeGenerator() {
        ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.setProperty("parser.space_gobbling", "lines");
        ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        ve.init();
    }

    public void reanderFeignClient(String serviceName, String rootPackage, ApiDocs apiDocs) {
        String optName = "reanderFeignClient";

        VelocityContext context = new VelocityContext();
        context.put("serviceName", serviceName);
        context.put("rootPackage", rootPackage);
        context.put("apiDocs", apiDocs);
        context.put("StringUtils", StringUtils.class);

        Template template = null;
        try {

            File file = new File("out/" + StringUtils.upperCamel(serviceName) + ".java");
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            if(file.exists()){
                file.delete();
            }
            log.info("File path: {}", file.getAbsolutePath());
            template = ve.getTemplate("templates/FeignClient.vm");
//            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8"));
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
            template.merge(context, osw);
            osw.close();
        } catch (Exception e) {
            log.error("{}异常!", optName, e);
        }
    }

}
