package com.kzk.kcodegen.tools;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DurationFormatUtils;

/**
 * @author kazeki
 */
public class TimingUtil {

    public static final String FORMAT_DEFAULT = "HH:mm:ss.SSS";

    private TimingUtil(){

    }

    public static long runAndTiming(Runnable fun){
        Long t0 = System.currentTimeMillis();
        fun.run();
        Long t1 = System.currentTimeMillis();
        return t1 - t0;
    }

    public static String runAndTimingWithFormat(Runnable fun, String format){
        return format(runAndTiming(fun), format);
    }

    public static String runAndTimingWithFormat(Runnable fun){
        return format(runAndTiming(fun), null);
    }

    public static String format(long timeCostMillis, String format){
        if(StringUtils.isBlank(format)){
            format = FORMAT_DEFAULT;
        }
        return DurationFormatUtils.formatDuration(timeCostMillis, format);
    }

}
