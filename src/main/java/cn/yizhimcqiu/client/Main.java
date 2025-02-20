package cn.yizhimcqiu.client;

import cn.yizhimcqiu.client.util.ILogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = ILogManager.getLogger();
    public static void main(String[] args) {
        LOGGER.info("Launching Meteor Shower......");
        new MeteorShowerClient().start();
    }

    static {
        System.setProperty("apple.awt.application.appearance", "system");
    }
}