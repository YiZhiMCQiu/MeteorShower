package cn.yizhimcqiu.state;

import cn.yizhimcqiu.util.ILogManager;
import cn.yizhimcqiu.util.TaskListBuilder;
import cn.yizhimcqiu.world.World;
import cn.yizhimcqiu.world.player.Player;
import cn.yizhimcqiu.world.position.Pos;
import cn.yizhimcqiu.world.position.Region;
import cn.yizhimcqiu.world.tile.Tiles;
import org.apache.logging.log4j.Logger;

public class GameState {
    private static final Logger LOGGER = ILogManager.getLogger();
    public static final Region WORLD_REGION = new Region(-256, -256, 256, 256);

    public Timer timer;
    public World world;
    public Player player;
    private static GameState instance;
    public boolean inGame = false;
    public final TaskListBuilder setupTasks = new TaskListBuilder();

    public void setup() {
        if (inGame) return;

        this.timer = new Timer();
        this.world = new World(WORLD_REGION);
        this.player = new Player(new Pos(0, 0));

        setupTasks.execute();

        LOGGER.info("Game state initialized");

        inGame = true;

        new Thread("Server-Ticker") {
            @Override
            public void run() {
                while (inGame) {
                    tick();
                }
            }
        }.start();
    }

    public void tick() {
        world.tick();
        player.tick();
        timer.tick();
    }


    public GameState() {
        instance = this;
        setupTasks.add(Tiles::init);
    }

    public static GameState getInstance() {
        return instance == null ? (instance = new GameState()) : instance;
    }


}
