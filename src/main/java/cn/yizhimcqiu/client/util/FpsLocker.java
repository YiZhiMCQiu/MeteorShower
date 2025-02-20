package cn.yizhimcqiu.client.util;

public class FpsLocker {
    private static long lastTick = 0;
    public static void tick() {
        long renderTime = System.currentTimeMillis() - lastTick;
        if (renderTime < 1000 / 60) {
            try {
                Thread.sleep(1000 / 60 - renderTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lastTick = System.currentTimeMillis();
    }
}