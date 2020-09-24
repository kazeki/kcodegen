package com.kzk.kcodegen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kazeki
 */
class KCodeGeneratorTest {

    @Test
    void renderDemo() {
        KCodeGenerator generator = new KCodeGenerator();
        String result = generator.renderDemo();
        assertNull(result);
    }

}
