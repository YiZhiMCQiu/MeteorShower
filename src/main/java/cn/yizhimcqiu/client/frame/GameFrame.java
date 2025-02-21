package cn.yizhimcqiu.client.frame;

import cn.yizhimcqiu.client.MeteorShowerClient;
import cn.yizhimcqiu.client.frame.font.Fonts;
import cn.yizhimcqiu.client.frame.renderer.TileRenderer;
import cn.yizhimcqiu.client.texture.TextureManager;
import cn.yizhimcqiu.client.util.FpsLocker;
import cn.yizhimcqiu.state.GameState;
import cn.yizhimcqiu.util.ILogManager;
import cn.yizhimcqiu.world.tile.Tiles;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame {
    private static final Logger LOGGER = ILogManager.getLogger();
    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 600;
    private final GamePanel gamePanel;
    private boolean running = true;

    public GameFrame() {
        this.setTitle("Meteor Shower");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.add(this.gamePanel = new GamePanel());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GameFrame.this.running = false;
                LOGGER.info("Game closed");
                System.exit(0);
            }
        });

        this.addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                MeteorShowerClient.getInstance().zoom += -e.getUnitsToScroll() / 10.0F;
            }
        });

        this.setVisible(true);
        LOGGER.info("Window created");
    }

    public void loadTextures() {
        TextureManager.loadTexture("stone", "stone.png");
        TextureManager.loadTexture("grass_block", "grass_block.png");
        TextureManager.loadTexture("glass", "glass.png");
        TextureManager.loadTexture("planks", "planks.png");
    }

    public void render() {
        this.gamePanel.repaint();
    }

    public boolean shouldClose() {
        return !this.running;
    }

    private class GamePanel extends JPanel {
        private boolean flag = true;
        private final Color bgColor = Color.CYAN;
        private final TileRenderer tileRenderer1 = new TileRenderer(Tiles.PLANKS);
        private final TileRenderer tileRenderer2 = new TileRenderer(Tiles.PLANKS);

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            if (MeteorShowerClient.getInstance().gameFrame == null) return;

            Graphics2D g2d = (Graphics2D) g;
            if (flag) {
                flag = false;
                GameFrame.this.initFont();
            }

            g2d.setColor(bgColor);
            g2d.fillRect(0, 0, getWidth(), getHeight());

//            tileRenderer1.render(g2d, 0, 0, 10);
//            tileRenderer2.render(g2d, 0, 1, 10);
//            tileRenderer1.render(g2d, 0, 2, 10);
//
//            tileRenderer1.render(g2d, 1, 0, 10);
//            tileRenderer2.render(g2d, 1, 1, 10);
//            tileRenderer1.render(g2d, 1, 2, 10);
//
//            tileRenderer1.render(g2d, 2, 0, 10);
//            tileRenderer2.render(g2d, 2, 1, 10);
//            tileRenderer1.render(g2d, 2, 2, 10);
            TileRenderer.buildRenderTasks(tileRenderer1, g2d).execute();

            FpsLocker.tick();
        }
    }

    private void initFont() {
        this.gamePanel.getGraphics().setFont(Fonts.PLAIN);
    }
}
