package cn.yizhimcqiu.registry;

import cn.yizhimcqiu.util.ILogManager;
import org.apache.logging.log4j.Logger;

public abstract class Registry<T> {
    private static final Logger LOGGER = ILogManager.getLogger();

    public abstract void register(ResourceLocator locator, T entry);
    public abstract T getByLocator(ResourceLocator locator);
    public abstract ResourceLocator getLocator(T entry);
    public abstract boolean contains(T entry);
    public abstract boolean containsLocator(ResourceLocator locator);

    public static <T> T register(Registry<T> registry, ResourceLocator locator, T entry) {
        if (registry.contains(entry)) {
            LOGGER.error("The value {} ({}) already exists in the registry {}!", entry, locator, registry);
            return entry;
        }
        if (registry.containsLocator(locator)) {
            LOGGER.error("The key {} ({}) already exists in the registry {}!", locator, entry, registry);
        }
        registry.register(locator, entry);
        return entry;
    }
}
