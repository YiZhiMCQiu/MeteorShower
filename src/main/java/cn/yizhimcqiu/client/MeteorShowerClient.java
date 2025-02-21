package cn.yizhimcqiu.client;

import cn.yizhimcqiu.client.frame.GameFrame;
import cn.yizhimcqiu.util.ExceptionHandler;
import cn.yizhimcqiu.util.ILogManager;
import cn.yizhimcqiu.state.GameState;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

public class MeteorShowerClient {
    private static final Logger LOGGER = ILogManager.getLogger();
    private static MeteorShowerClient instance;
    public Thread renderThread;
    public GameFrame gameFrame;
    public Queue<Runnable> taskQueue = new LinkedList<>();
    public float zoom = 1;

    public MeteorShowerClient() {
        instance = this;

        this.gameFrame = new GameFrame();
        this.gameFrame.loadTextures();
        LOGGER.info("Game initialized");

        ExceptionHandler.CRASH_TASKS.add(MeteorShowerClient::new);

        GameState.getInstance().setup();
    }

    public void start() {
        this.renderThread = new Thread("Render thread") {
            @Override
            public void run() {
                while (!MeteorShowerClient.this.gameFrame.shouldClose()) {
                    MeteorShowerClient.this.gameFrame.render();
                    Queue<Runnable> queue = MeteorShowerClient.this.taskQueue;
                    if (!queue.isEmpty()) {
                        queue.poll().run();
                    }

                    MeteorShowerClient.this.zoom = Math.clamp(MeteorShowerClient.this.zoom, 1, 5);
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

    public void scheduleTask(Runnable task) {
        this.taskQueue.offer(task);
    }
}
