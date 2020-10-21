package com.kzk.kcodegen.cli;

import com.beust.jcommander.Parameter;
import com.kzk.kcodegen.core.KCodeGenerator;
import com.kzk.kcodegen.core.KCodeGeneratorVelocityImpl;
import lombok.Data;

import java.net.MalformedURLException;

/**
 * @author kazeki
 * <p>
 * JCommander
 * https://blog.csdn.net/u013128262/article/details/84556077
 * <p>
 */
@Data
public class KCodeGenCliActionBase extends KCliActionBase{

    private KCodeGenerator kCodeGenerator;
    @Parameter(names = {"--velocityConfig"}, description = "velocity配置文件")
    private String velocityConfig = "conf/velocity.properties";

    @Override
    public void excute() throws MalformedURLException, Exception {
        kCodeGenerator = new KCodeGeneratorVelocityImpl(velocityConfig);
    }
}
