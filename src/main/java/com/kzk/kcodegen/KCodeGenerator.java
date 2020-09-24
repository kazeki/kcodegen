package com.kzk.kcodegen;

import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

/**
 * @author kazeki
 */
@Slf4j
public class KCodeGenerator {
    VelocityEngine ve;

    public KCodeGenerator() {
        ve = new VelocityEngine();
        ve.addProperty("parser.space_gobbling", "lines");
    }

    public String renderDemo() {
        String optName = "渲染Demo";
        VelocityContext context = new VelocityContext();
        context.put("name", "velocity");

        Template template = null;

        try {
            template = ve.getTemplate("templates/hello.vm");
        } catch (Exception e) {
            log.error("{}异常!", optName, e);
        }


        StringWriter sw = new StringWriter();

        if (template != null) {
            template.merge(context, sw);
        }

        return sw.toString();
    }
}
