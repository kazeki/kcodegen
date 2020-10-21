package com.kzk.kcodegen.cli;

import com.beust.jcommander.JCommander;

/**
 * @author kazeki
 */
public class MainCliAction extends KCliActionBase{
    private final JCommander jCommander;
//    private HelloCodeGenParam helloCodeGenParam = new HelloCodeGenParam();
//    private FeignClientCodeGenCliAction feignClientCodeGenParam = new FeignClientCodeGenCliAction();

    public static void main(String[] args) throws Exception {
        MainCliAction cli = new MainCliAction(new String[]{"hello"});
//        cli.excute(new String[]{"feignClient"});
        cli.excute();
    }
    
    public MainCliAction(String[] args){
        jCommander = JCommander.newBuilder()
            .programName("kCodeGen")
            .addObject(this)
            .addCommand("hello", new HelloCodeGenCliAction(), new String[]{})
            .addCommand("feignClient", new FeignClientCodeGenCliAction(), new String[]{})
            .build();
        jCommander.parse(args);
    }

    @Override
    public void excute() throws Exception {
        String parsedCommand = jCommander.getParsedCommand();
        if (parsedCommand == null) {
            if (isArgHelp()) {
                jCommander.usage();
                return;
            }

            if (isArgVersion()) {
                jCommander.getConsole().println(getVersion());
                return;
            }
            jCommander.usage();
            return;
        } else {
            JCommander subCommand = jCommander.getCommands().get(parsedCommand);
            KCliActionBase subAction = (KCliActionBase) (subCommand.getObjects().get(0));
            if(subAction.isArgHelp()){
                subCommand.usage();
                return;
            }
            if (subAction.isArgVersion()) {
                jCommander.getConsole().println(subAction.getVersion());
                return;
            }
            subAction.excute();
        }
    }
}
