package com.kzk.kcodegen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author kazeki
 */
class KCodeGeneratorTest {

    @Test
    void renderDemo() {
        KCodeGenerator generator = new KCodeGenerator();
        String result = generator.renderDemo();
        assertEquals("Hello, velocity", result);
    }

}
