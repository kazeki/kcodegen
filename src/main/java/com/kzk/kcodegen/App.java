package com.kzk.kcodegen;

/**
 * @author kazeki
 */
public class App {
    public static void main(String[] args) {
        KCodeGenerator generator = new KCodeGenerator();
        System.out.println(generator.renderDemo());
    }
}
