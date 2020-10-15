package com.kzk.kcodegen;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.net.URL;

/**
 * @author kazeki
 * <p>
 * JCommander
 * https://blog.csdn.net/u013128262/article/details/84556077
 * <p>
 */
public class KCodeGenCLI extends AbstractCLI {

    public static final String PROGRAM_NAME = "KCodeGen";
    public static final String VERSION = "1.0.0";

    public static final String TASK_GEN_FEIGN_CLIENT_FROM_APIDOCS = "feign";

    @Parameter(description = "任务类型(可选：feign)", order = 1)
    private String task = TASK_GEN_FEIGN_CLIENT_FROM_APIDOCS;

    @Parameter(names = {"--velocityConfig"}, description = "velocity配置文件", order = 2)
    private String velocityConfig = "conf/velocity.properties";

    @Parameter(names = {"--serviceName"}, description = "服务名称", order = 2)
    private String serviceName = "demo-service";
    @Parameter(names = {"--rootPackage"}, description = "包名", order = 2)
    private String rootPackage = "com.demo.api";
    @Parameter(names = {"--outputDir"}, description = "输出路径", order = 2)
    private String outputDir = "output/demo";
    @Parameter(names = {"--input"}, description = "输入资源", order = 2)
    private String input = "input/demo/apidocs.json";

    public static void main(String[] args) {
        KCodeGenCLI KCodeGenCli = new KCodeGenCLI();
        KCodeGenCli.excute(args);
    }

    @Override
    protected String getProgramName() {
        return PROGRAM_NAME;
    }

    @Override
    protected String getVersion() {
        return VERSION;
    }

    @Override
    protected void mainProcess(JCommander jCommander) throws Exception {
        if(TASK_GEN_FEIGN_CLIENT_FROM_APIDOCS.equals(task)){
            if(StringUtils.isBlank(rootPackage)){
                throw new ParameterException("rootPackage未指定");
            }
            if(StringUtils.isBlank(input)){
                throw new ParameterException("inputSource未指定");
            }

            ClientSideApiGenerator kcg = new ClientSideApiGenerator(velocityConfig);
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ApiDocs apiDocs = null;
            if(input.startsWith("http")){
                apiDocs = om.readValue(new URL(input), ApiDocs.class);
            } else {
                apiDocs = om.readValue(new File(input), ApiDocs.class);
            }
            kcg.render(serviceName, rootPackage, apiDocs, outputDir);
        } else {
            throw new ParameterException("不支持的任务：" + task);
        }
    }
}
