package cn.yizhimcqiu.client.frame.renderer;

import cn.yizhimcqiu.client.MeteorShowerClient;
import cn.yizhimcqiu.client.frame.GameFrame;
import cn.yizhimcqiu.client.texture.TextureManager;
import cn.yizhimcqiu.registry.Registries;
import cn.yizhimcqiu.state.GameState;
import cn.yizhimcqiu.util.TaskListBuilder;
import cn.yizhimcqiu.world.position.Pos;
import cn.yizhimcqiu.world.tile.Tile;

import java.awt.*;
import java.awt.image.ImageObserver;

public class TileRenderer implements ImageObserver {
    public static final int LEN = 16;
    private final MeteorShowerClient client = MeteorShowerClient.getInstance();
    private final String textureId;

    public TileRenderer(Tile tile) {
        this.textureId = Registries.TILE.getLocator(tile).path();
    }

    public void render(Graphics2D g2d, int x, int y, int light) {
        Image originalImage = TextureManager.TEXTURES.get(this.textureId);
        g2d.drawImage(originalImage,
                // Math.round(x * LEN * this.client.zoom), Math.round(y * LEN * this.client.zoom),
                // Math.round(LEN * this.client.zoom), Math.round(LEN * this.client.zoom),
                x * LEN, y * LEN,
                LEN, LEN,
                this);
    }

    public static TaskListBuilder buildRenderTasks(TileRenderer renderer, Graphics2D g2d) {
        TaskListBuilder builder = new TaskListBuilder();
        if (!GameState.getInstance().inGame) return builder;
        Pos pos = GameState.getInstance().player.getPos();
        MeteorShowerClient client = MeteorShowerClient.getInstance();
        GameFrame gameFrame = client.gameFrame;
        double halfW = (double) gameFrame.getWidth() / LEN / 2;
        double halfH = (double) gameFrame.getHeight() / LEN / 2;
        int x = (int) Math.floor((pos.x - halfW) / client.zoom);
        int y = (int) Math.floor((pos.y - halfH) / client.zoom);
        int width = (int) Math.ceil((pos.y + halfW) / client.zoom);
        int height = (int) Math.ceil((pos.y + halfH) / client.zoom);
        g2d.translate(halfW * LEN, halfH * LEN);
        g2d.scale(client.zoom, client.zoom);
        g2d.translate(-pos.x * LEN, -pos.y * LEN);
        for (int i = x; i <= width; ++i) {
            for (int j = y; j <= height; ++j) {
                int finalI = i;
                int finalJ = j;
                builder.add(() -> new TileRenderer(GameState.getInstance().world.getTileAt(finalI, finalJ)).render(g2d, finalI, finalJ, 10));
            }
        }
        return builder;
    }

    @Override
    public boolean imageUpdate(Image img, int infoFlags, int x, int y, int width, int height) {
        return false;
    }
}
