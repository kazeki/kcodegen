package com.kzk.kcodegen.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.kzk.kcodegen.app.HelloGenerator;
import lombok.Data;

/**
 * @author kazeki
 * <p>
 * JCommander
 * https://blog.csdn.net/u013128262/article/details/84556077
 * <p>
 */
@Data
@Parameters(commandDescription = "生成FeignClient代码")
public class HelloCodeGenCliAction extends KCodeGenCliActionBase {

    @Parameter(names = {"--outputDir"}, description = "输出路径")
    private String outputDir = "output";

    @Override
    public void excute() throws Exception {
        super.excute();
        HelloGenerator helloGenerator = new HelloGenerator(getKCodeGenerator());
        helloGenerator.render(outputDir);
    }
}
