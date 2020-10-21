package com.kzk.kcodegen.cli;

import com.beust.jcommander.Parameter;
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
public abstract class KCliActionBase {

    @Parameter(names = {"-h", "--help"}, description = "帮助", help = true)
    private boolean argHelp;

    @Parameter(names = {"-v", "--version"}, description = "版本")
    private boolean argVersion;

    public abstract  void excute() throws MalformedURLException, Exception;

    public String getVersion(){
        return "1.0.0";
    }

}
