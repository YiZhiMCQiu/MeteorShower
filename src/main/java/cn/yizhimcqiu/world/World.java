package cn.yizhimcqiu.world;

import cn.yizhimcqiu.world.position.ChunkPos;
import cn.yizhimcqiu.world.position.Region;
import cn.yizhimcqiu.world.tile.Tile;
import cn.yizhimcqiu.world.tile.Tiles;

import java.util.HashMap;
import java.util.Map;

public class World {
    private final Region region;
    private final Map<ChunkPos, Chunk> chunks = new HashMap<>();

    public World(Region worldRegion) {
        this.region = worldRegion;
    }

    public void tick() {

    }

    public Region getRegion() {
        return region;
    }

    public Chunk getChunkAt(ChunkPos chunkPos) {
        if (!chunks.containsKey(chunkPos)) {
            chunks.put(chunkPos, new Chunk(chunkPos));
        }
        return chunks.get(chunkPos);
    }

    public Chunk getChunkAt(int x, int y) {
        return getChunkAt(new ChunkPos(x / 16, y / 16));
    }

    public Tile getTileAt(int x, int y) {
        if (x == 0 && y == 0) return Tiles.AIR;
        return getChunkAt(x, y).getTileAt(Math.abs(x % 16), Math.abs(y % 16));
    }
}
