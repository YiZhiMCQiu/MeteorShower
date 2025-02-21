package cn.yizhimcqiu.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegistryImpl<T> extends Registry<T> {
    private final Map<ResourceLocator, T> locatorEntryMap = new HashMap<>();
    private final Map<T, ResourceLocator> entryLocatorMap = new HashMap<>();

    @Override
    public void register(ResourceLocator locator, T entry) {
        Objects.requireNonNull(locator);
        Objects.requireNonNull(entry);
        locatorEntryMap.put(locator, entry);
        entryLocatorMap.put(entry, locator);
    }

    @Override
    public T getByLocator(ResourceLocator locator) {
        return locatorEntryMap.get(locator);
    }

    @Override
    public ResourceLocator getLocator(T entry) {
        return entryLocatorMap.get(entry);
    }

    @Override
    public boolean contains(T entry) {
        return locatorEntryMap.containsValue(entry);
    }

    @Override
    public boolean containsLocator(ResourceLocator locator) {
        return locatorEntryMap.containsKey(locator);
    }
}
