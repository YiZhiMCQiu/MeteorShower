package cn.yizhimcqiu.world.position;

import cn.yizhimcqiu.world.tile.Tile;

import java.util.Objects;

public class ChunkPos {
    public static final int SIZE = 16;

    private final int x;
    private final int y;
    private final Region region;

    public ChunkPos(int x, int y) {
        this.x = x;
        this.y = y;
        this.region = new Region(x * 16, x * 16, (x + 1) * 16, (y + 1) * 16);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Region getRegion() {
        return region;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ChunkPos chunkPos)) return false;
        return x == chunkPos.x && y == chunkPos.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
