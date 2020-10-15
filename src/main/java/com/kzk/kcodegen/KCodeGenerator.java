package com.kzk.kcodegen;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Map;

/**
 * @author kazeki
 */
@Slf4j
public class KCodeGenerator {
    private VelocityEngine ve;
    // TODO: macroLibraries
    private List macroLibraries;

    public KCodeGenerator() {
        this.ve = new VelocityEngine();
        this.ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        this.ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        this.ve.setProperty("parser.space_gobbling", "lines");
        this.ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        this.ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        this.ve.init();
    }

    public KCodeGenerator(String propsFilename) {
        this.ve = new VelocityEngine(propsFilename);
        this.ve.init();
    }

    private VelocityContext initVelocityContext(Map<String, Object> datas) {
        VelocityContext velocityContext = new VelocityContext();
        if (datas != null) {
            datas.keySet().stream().forEach(key -> {
                velocityContext.put(key, datas.get(key));
            });
        }
        velocityContext.put("genBy", "KCodeGen(Velocity)");
        velocityContext.put("genOn", DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        velocityContext.put("StringUtils", StringUtils.class);
        return velocityContext;
    }

    public void render(String templateId, Map<String, Object> datas, String targetPath, boolean overwriteOnExists) throws IOException {
        Preconditions.checkNotNull(templateId, "模板未指定");
        Preconditions.checkNotNull(targetPath, "输出路径未指定");
        File file = new File(targetPath);
        log.info("Target path: {}", file.getAbsolutePath());
        VelocityContext velocityContext = initVelocityContext(datas);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            if (overwriteOnExists) {
                file.delete();
            } else {
                throw new FileAlreadyExistsException(file.getAbsolutePath());
            }
        }

        String templatePath = getTemplatePath(templateId);

        Template template = ve.getTemplate(templatePath);
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file))) {
            template.merge(velocityContext, osw, macroLibraries);
        }
    }

    private String getTemplatePath(String templateId) {
        return "templates/" + templateId + ".vm";
    }

}
