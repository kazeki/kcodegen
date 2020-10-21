package com.kzk.kcodegen.core;

import java.io.IOException;
import java.util.Map;

/**
 * @author kazeki
 */
public interface KCodeGenerator {
    void render(String templateId, Map<String, Object> datas, String targetPath, boolean overwriteOnExists) throws IOException;
}
