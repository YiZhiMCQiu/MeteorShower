package cn.yizhimcqiu.client.frame;

import cn.yizhimcqiu.client.MeteorShowerClient;
import cn.yizhimcqiu.client.frame.font.Fonts;
import cn.yizhimcqiu.client.frame.macos.menuBar.MenuItemBuilder;
import cn.yizhimcqiu.client.frame.macos.touchBar.TouchBarButtonImpl;
import cn.yizhimcqiu.client.frame.renderer.BlockRenderer;
import cn.yizhimcqiu.client.texture.TextureManager;
import cn.yizhimcqiu.client.util.FieldHelper;
import cn.yizhimcqiu.client.util.FpsLocker;
import cn.yizhimcqiu.client.util.ILogManager;
import com.thizzer.jtouchbar.JTouchBar;
import com.thizzer.jtouchbar.item.TouchBarItem;
import com.thizzer.jtouchbar.item.view.TouchBarButton;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        this.setMenuBar(this.createMenuBar());
        this.add(this.gamePanel = new GamePanel());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GameFrame.this.running = false;
                LOGGER.info("Game closed");
                System.exit(0);
            }
        });

        this.setVisible(true);
        LOGGER.info("Window created");

        this.initTouchBar();
    }

    private void initTouchBar() {
//        JTouchBar touchBar = new JTouchBar();
//        touchBar.addItem(new TouchBarButtonImpl().createItem());
//        touchBar.show(this);
    }

    public void loadTextures() {
        TextureManager.loadTexture("stone", "stone.png");
        TextureManager.loadTexture("grass_block", "grass_block.png");
        TextureManager.loadTexture("glass", "glass.png");
    }
    public void render() {
        this.gamePanel.repaint();
    }

    public boolean shouldClose() {
        return !this.running;
    }

    private class GamePanel extends JPanel {
        private boolean flag = true;
        private Color bgColor;
        private final BlockRenderer blockRenderer1 = new BlockRenderer("grass_block");
        private final BlockRenderer blockRenderer2 = new BlockRenderer("stone");
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

            blockRenderer1.render(g2d, 0, 0, 0, 10);
            blockRenderer2.render(g2d, 0, 1, 0, 10);
            blockRenderer1.render(g2d, 0, 2, 0, 10);

            blockRenderer1.render(g2d, 1, 0, 0, 10);
            blockRenderer2.render(g2d, 1, 1, 0, 10);
            blockRenderer1.render(g2d, 1, 2, 0, 10);

            blockRenderer1.render(g2d, 2, 0, 0, 10);
            blockRenderer2.render(g2d, 2, 1, 0, 10);
            blockRenderer1.render(g2d, 2, 2, 0, 10);

            FpsLocker.tick();
        }
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("背景颜色");
        for (Object obj : FieldHelper.getStaticFields(Color.class, Color.class, field -> field.getName().equals(field.getName().toUpperCase()) && field.getType() == Color.class)) {
            Color color = (Color) obj;
            menu.add(new MenuItemBuilder().label(color.toString()).shortcutKey(KeyEvent.VK_A)
                    .addListener(e -> this.gamePanel.bgColor = color).build());
        }
        menuBar.add(menu);
        return menuBar;
    }
    private void initFont() {
        this.gamePanel.getGraphics().setFont(Fonts.PLAIN);
    }
}
