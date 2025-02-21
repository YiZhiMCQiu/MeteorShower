package cn.yizhimcqiu.client.texture;

import cn.yizhimcqiu.util.ILogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class TextureManager {
    private static final Logger LOGGER = ILogManager.getLogger();
    public static final Map<String, BufferedImage> TEXTURES = new HashMap<>();
    public static void loadTexture(String name, String path) {
        URL resource = TextureManager.class.getClassLoader().getResource(path);
        if (resource == null || !Path.of(resource.getPath()).toFile().exists()) {
            LOGGER.error("Texture not found: {}", path);
            return;
        }
        try {
            TEXTURES.put(name, ImageIO.read(resource));
        } catch (IOException e) {
            LOGGER.error("Error while loading texture", e);
        }
    }
}
