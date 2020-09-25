package com.kzk.kcodegen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kazeki
 */
class AppTest {

    @Test
    void main() {
        assertDoesNotThrow(()->App.main(new String[0]), "Main执行失败");
    }
}