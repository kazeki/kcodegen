package com.kzk.kcodegen;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author kazeki
 */
class GuavaTaste {

    @Test
    void testPassSingleData() {
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        StringWriter sw = new StringWriter();
        Context ctx = new VelocityContext();
        ctx.put("name", "Velocity");
        ve.evaluate(ctx, sw, "ltPassData", "${name}");
        assertEquals("Velocity", sw.toString());
    }

    @Test
    void testPassArrayAndDisplayByForeach() {
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        StringWriter sw = new StringWriter();
        Context ctx = new VelocityContext();
        String[] names = new String[]{"Abc","Def"};
        ctx.put("names", names);
        ve.evaluate(ctx, sw, "ltPassData", "#foreach($name in ${names})" +
            "$name\n" +
            "#end");
        assertEquals("Abc\n" +
            "Def\n", sw.toString());
    }

    @Test
    void testPassToolClassAndCallToolMethodFromTemplate() {
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        StringWriter sw = new StringWriter();
        Context ctx = new VelocityContext();
        String[] names = new String[]{"Abc","Def"};
        ctx.put("names", names);
        ctx.put("String", String.class);
        ve.evaluate(ctx, sw, "ltPassData", "${String.join(\",\", $names)}");
        assertEquals("Abc,Def", sw.toString());
    }

    @Test
    void testLoadTemplateOnClasspath() {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        assertDoesNotThrow(()->{
            ve.getTemplate("templates/hello.vm");
        });
    }

//    @Test
//    void testStringUtils(){
//        assertEquals("AbcDef", StringUtils.firstLetterCaps("abc def"), "1");
//        assertEquals("AbcDef", StringUtils.capitalizeFirstLetter("abc-def"), "2");
//        assertEquals("AbcDef", StringUtils.capitalizeFirstLetter("abc_def"),"3");
//    }

}
