package cn.yizhimcqiu.registry;

public record ResourceLocator(String parent, String path) {
    public ResourceLocator(String path) {
        this("meteorshower", path);
    }

    @Override
    public String toString() {
        return parent + ":" + path;
    }
}
