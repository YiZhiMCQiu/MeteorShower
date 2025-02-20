package cn.yizhimcqiu.client;

import cn.yizhimcqiu.client.frame.GameFrame;
import cn.yizhimcqiu.client.util.ILogManager;
import org.apache.logging.log4j.Logger;

public class MeteorShowerClient {
    private static final Logger LOGGER = ILogManager.getLogger();
    private static MeteorShowerClient instance;
    public Thread renderThread;
    public GameFrame gameFrame;

    public MeteorShowerClient() {
        instance = this;

        this.gameFrame = new GameFrame();
        this.gameFrame.loadTextures();
        LOGGER.info("Game initialized");
    }

    public void start() {
        this.renderThread = new Thread("Render thread") {
            @Override
            public void run() {
                while (!MeteorShowerClient.this.gameFrame.shouldClose()) {
                    MeteorShowerClient.this.gameFrame.render();
                }
            }
        };
        LOGGER.info("Render thread created");
        this.renderThread.start();
        LOGGER.info("Game start~");
    }

    public static MeteorShowerClient getInstance() {
        return instance;
    }
}
