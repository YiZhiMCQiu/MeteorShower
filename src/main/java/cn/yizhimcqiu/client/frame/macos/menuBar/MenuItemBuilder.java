package cn.yizhimcqiu.client.frame.macos.menuBar;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuItemBuilder {
    private String label = "";
    private Integer shortcutKey = null;
    private final List<ActionListener> listeners = new ArrayList<>();
    public MenuItemBuilder label(String label) {
        this.label = label;
        return this;
    }
    public MenuItemBuilder shortcutKey(int shortcutKey) {
        this.shortcutKey = shortcutKey;
        return this;
    }
    public MenuItemBuilder addListener(ActionListener listener) {
        this.listeners.add(listener);
        return this;
    }
    public MenuItem build() {
        MenuItem menuItem;
        if (shortcutKey == null) {
            menuItem = new MenuItem(label);
        } else {
            menuItem = new MenuItem(label, new MenuShortcut(shortcutKey));
        }
        for (ActionListener listener : listeners) {
            menuItem.addActionListener(listener);
        }
        return menuItem;
    }
}
