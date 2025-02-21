package cn.yizhimcqiu.client;

import cn.yizhimcqiu.client.macosx.MacOSApplication;
import cn.yizhimcqiu.util.ExceptionHandler;
import cn.yizhimcqiu.util.ILogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = ILogManager.getLogger();
    public static void main(String[] args) {
        LOGGER.info("Launching Meteor Shower......");
        if (System.getProperty("os.name").equals("Mac OS X")) {
            MacOSApplication.init();
        }
        ExceptionHandler.safe(() -> new MeteorShowerClient().start());
    }
}