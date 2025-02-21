package cn.yizhimcqiu.state;

public class Timer {
    public static final int TICK_DELAY = 1000 / 20;

    private long lastTick;
    private final int tickDelay;
    private int tickCount;
    private double tps;

    public Timer() {
        this.tickDelay = TICK_DELAY;
    }

    public Timer(int tickDelay) {
        this.tickDelay = tickDelay;
    }

    public void tick() {
        if (tickDelay - lastTick > 0) {
            try {
                Thread.sleep(tickDelay - lastTick); // 被哄睡着了
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        tps = 1000.0 / (System.currentTimeMillis() - lastTick);
        lastTick = System.currentTimeMillis();
    }

    public double getTps() {
        return tps;
    }

    public int getTickCount() {
        return tickCount;
    }
}
