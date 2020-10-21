package com.kzk.kcodegen.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kzk.kcodegen.app.ClientSideApiGenerator;
import com.kzk.kcodegen.model.ApiDocs;
import lombok.Data;

import java.io.File;
import java.net.URL;

/**
 * @author kazeki
 * <p>
 * JCommander
 * https://blog.csdn.net/u013128262/article/details/84556077
 * <p>
 */
@Data
@Parameters(commandDescription = "生成FeignClient代码")
public class FeignClientCodeGenCliAction extends KCodeGenCliActionBase {
    @Parameter(names = {"--serviceName"}, description = "服务名称")
    private String serviceName = "demo-service";
    @Parameter(names = {"--rootPackage"}, description = "包名")
    private String rootPackage = "com.demo.api";
    @Parameter(names = {"--outputDir"}, description = "输出路径")
    private String outputDir = "output/demo";
    @Parameter(names = {"--input"}, description = "输入资源")
    private String input = "input/demo/apidocs.json";

    @Override
    public void excute() throws Exception {
        super.excute();
        ClientSideApiGenerator clientSideApiGenerator = new ClientSideApiGenerator(getKCodeGenerator());
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ApiDocs apiDocs = null;
        String input = getInput();
        if (input.startsWith("http")) {
            apiDocs = om.readValue(new URL(input), ApiDocs.class);
        } else {
            apiDocs = om.readValue(new File(input), ApiDocs.class);
        }
        clientSideApiGenerator.render(getServiceName(),
            getRootPackage(),
            apiDocs,
            getOutputDir());
    }
}
