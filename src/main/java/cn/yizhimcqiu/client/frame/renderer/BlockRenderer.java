package cn.yizhimcqiu.client.frame.renderer;

import cn.yizhimcqiu.client.MeteorShowerClient;
import cn.yizhimcqiu.client.texture.TextureManager;

import java.awt.*;
import java.awt.image.ImageObserver;

public class BlockRenderer implements ImageObserver {
    public static final int LEN = 200;
    private final MeteorShowerClient client = MeteorShowerClient.getInstance();
    private final String textureId;
    public BlockRenderer(String textureId) {
        this.textureId = textureId;
    }
    public void render(Graphics2D g2d, int x, int y, int z, int light) {
        Image originalImage = TextureManager.TEXTURES.get(this.textureId);

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawImage(originalImage, this.client.gameFrame.getWidth()/10*3+x*(LEN-110), this.getScreenY(x, y, z), LEN, LEN, this);
        g2d.drawImage(TextureManager.TEXTURES.get("glass"),
                this.client.gameFrame.getWidth()/10*3+x*(LEN-110), this.getScreenY(x, y, z), LEN, LEN, this);
    }
    private int getScreenY(int x, int y, int z) {
        return (int) ((this.client.gameFrame.getHeight()-y*109)-LEN*1.5+x*LEN*0.2265);
    }
    @Override
    public boolean imageUpdate(Image img, int infoFlags, int x, int y, int width, int height) {
        return false;
    }
}
