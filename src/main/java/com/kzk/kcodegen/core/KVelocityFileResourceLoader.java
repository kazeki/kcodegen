package com.kzk.kcodegen.core;

/**
 * @author kazeki
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;

import java.io.*;

@Slf4j
public class KVelocityFileResourceLoader extends FileResourceLoader {
    @Override
    public synchronized InputStream getResourceStream(String name) {
        InputStream is = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(super.getResourceStream(name), "utf-8"));
             StringWriter sw = new StringWriter()) {
            br.lines().forEach(line -> sw.write(preProcessTemplateLine(line)));
            is = new ByteArrayInputStream(sw.toString().getBytes("utf-8"));
        } catch (Exception e) {
            log.error("模板预处理异常", e);
        }
        return is;
    }

    private String preProcessTemplateLine(String line) {
        return line.replaceAll("^\\s*#", "#") + "\r";
    }

}