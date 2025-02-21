package cn.yizhimcqiu.client.frame.font;

import cn.yizhimcqiu.util.ILogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.Objects;

public class Fonts {
    private static final Logger LOGGER = ILogManager.getLogger();
    private static final Font BASE = createFont("/font/plain_text.ttf");
    public static final Font PLAIN = BASE.deriveFont(Font.PLAIN, 20);
    public static final Font BOLD = PLAIN.deriveFont(Font.BOLD);
    public static final Font ITALIC = PLAIN.deriveFont(Font.ITALIC);

    private static Font createFont(String path) {
        try {
            LOGGER.info("Loading font: {}", path);
            return Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(Fonts.class.getResourceAsStream(path)));
        } catch (Exception e) {
            LOGGER.error("Error while loading font", e);
            return null;
        }
    }
}
