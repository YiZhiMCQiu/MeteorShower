package cn.yizhimcqiu.client.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ILogManager {
    public static Logger getLogger() {
        return LogManager.getLogger(Arrays.stream(Thread.currentThread().getStackTrace()[2].getClassName().split("\\.")).toList().getLast());
    }
}
