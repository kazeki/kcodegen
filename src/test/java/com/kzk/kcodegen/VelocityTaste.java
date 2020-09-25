package com.kzk.kcodegen;

import com.google.common.base.CaseFormat;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.util.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author kazeki
 */
class VelocityTaste {



    @Test
    void testStringUtils(){
        assertEquals("abc-def", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN,"AbcDef"), "1");
        assertEquals("AbcDef", CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL,"abc-def"), "1");
    }

}
