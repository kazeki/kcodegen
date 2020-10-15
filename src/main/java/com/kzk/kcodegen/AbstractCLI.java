package com.kzk.kcodegen;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kazeki
 * <p>
 * JCommander
 * https://blog.csdn.net/u013128262/article/details/84556077
 */
@Slf4j
public abstract class AbstractCLI {

    private JCommander jCommander;

    @Parameter(names = {"-v", "--version"}, description = "显示当前版本", help = true, order = 998)
    private boolean cmdVersion;

    @Parameter(names = {"-h", "--help"}, description = "帮助", help = true, order = 999)
    private boolean cmdHelp;

    public AbstractCLI() {
        jCommander = JCommander.newBuilder().addObject(this).build();
        jCommander.setProgramName("java -jar " + getProgramName() + ".jar");
    }

    protected abstract String getProgramName();

    public void excute(String[] args){
        String timeCostStr = TimingUtil.runAndTimingWithFormat(() -> {
            try {
                jCommander.parse(args);
                if (cmdHelp) {
                    help();
                    return;
                }
                if (cmdVersion) {
                    version();
                    return;
                }
                mainProcess(jCommander);
            } catch (ParameterException pe){
                log.error("命令执参数错误[{}]", pe.getMessage());
                help();
            } catch (Exception e) {
                log.error("命令执行异常, args: {}", args, e);
            }
        });
        log.info("命令执行完毕，耗时：" + timeCostStr);
    }

    protected abstract void mainProcess(JCommander jCommander) throws Exception;

    protected void help() {
        jCommander.usage();
    }

    protected void version() {
        jCommander.getConsole().println(getVersion());
    }

    protected abstract String getVersion();


}
