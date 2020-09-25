package com.kzk.kcodegen;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kazeki
 */
@Slf4j
public class App {
    private App(){}

    public static void main(String[] args) {
        KCodeGenerator generator = new KCodeGenerator();
        log.info(generator.renderDemo());
    }
}
