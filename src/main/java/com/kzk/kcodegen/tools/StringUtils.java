package com.kzk.kcodegen.tools;

import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kazeki
 */
@Slf4j
public class StringUtils {
    public static String packageToPath(String packageStr) {
        return packageStr.replaceAll("\\.", "/");
    }

    public static String upperCamel(String str) {
        return CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, str);
    }

    public static String getModelNameFromRefStr(String refStr) {
        String res = "XXX";
        try {
            res = refStr.substring(refStr.lastIndexOf("/") + 1);
            res = res.replaceAll("[«»]", "__");
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
            } else if ("int64".equals(format)) {
                return "Long";
            } else {
                return "Integer";
            }
        }
        if ("string".equals(type)) {
            return "String";
        }
        if ("number".equals(type)) {
            if ("float".equals(format)) {
                return "Float";
            } else if ("double".equals(format)) {
                return "Double";
            } else {
                return "Double";
            }
        }
        return type;
    }
}
