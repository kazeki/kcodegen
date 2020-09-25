package com.kzk.kcodegen;

import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kazeki
 */
@Slf4j
public class StringUtils {
    public static String upperCamel(String str) {
        return CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, str);
    }

    public static String getModelNameFromRefStr(String refStr, ApiDocs.Path.Parameter parameter) {
        String res = "XXX";
        try {
            res = refStr.substring(refStr.lastIndexOf("/") + 1);
        } catch (Exception e) {
            log.error("getModelNameFromRefStr {}", refStr, e);
        }
        return res;
    }

    public static boolean isBlank(Object obj) {
        if (obj instanceof String) {
            return org.apache.commons.lang.StringUtils.isBlank((String) obj);
        } else {
            return obj == null;
        }
    }

    public static String javaType(String type, String format) {
        if ("integer".equals(type)) {
            if ("int32".equals(format)) {
                return "Integer";
            }
            if ("int64".equals(format)) {
                return "Long";
            }
        }
        if("string".equals(type)){
            return "String";
        }
        if("number".equals(type)){
            if ("float".equals(format)) {
                return "Float";
            }
            if ("double".equals(format)) {
                return "Double";
            }
        }
        return type;
    }
}
