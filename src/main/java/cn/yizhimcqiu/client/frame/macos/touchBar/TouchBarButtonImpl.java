package cn.yizhimcqiu.client.frame.macos.touchBar;

import cn.yizhimcqiu.client.texture.TextureManager;
import com.thizzer.jtouchbar.common.Image;
import com.thizzer.jtouchbar.item.TouchBarItem;
import com.thizzer.jtouchbar.item.view.TouchBarButton;

import java.io.IOException;

public class TouchBarButtonImpl extends TouchBarButton {
    @Override
    public void trigger() {
        System.out.println("awa");
    }

    @Override
    public String getTitle() {
        return "输出awa";
    }
    @Override
    public Image getImage() {
        try {
            return new Image(TextureManager.class.getResourceAsStream("/glass.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TouchBarItem createItem() {
        return new TouchBarItem("example", this, true);
    }
}
