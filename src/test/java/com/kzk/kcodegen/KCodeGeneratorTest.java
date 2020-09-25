package com.kzk.kcodegen;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kazeki
 */
class KCodeGeneratorTest {

    @Test
    void renderDemo() {
        File file = new File("templates/hello.vm");
        assertTrue(file.exists(), "模板不存在");

        KCodeGenerator generator = new KCodeGenerator();
        String result = generator.renderDemo();
        assertEquals("Hello, velocity", result, "渲染Demo失败");

        long ts = System.currentTimeMillis();
        File bakfile = new File("templates/hello.vm" + ts);
        assertTrue(file.renameTo(bakfile), "模板备份失败");

        String result2 = generator.renderDemo();
        assertEquals("", result2, "无模板场景执行失败");

        assertTrue(bakfile.renameTo(file), "恢复模板失败");
    }

}
