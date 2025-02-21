package cn.yizhimcqiu.world;

import cn.yizhimcqiu.world.position.ChunkPos;
import cn.yizhimcqiu.world.tile.Tile;
import cn.yizhimcqiu.world.tile.Tiles;

import java.util.Objects;

public class Chunk {
    private final ChunkPos pos;
    private final Tile[][] tiles = new Tile[ChunkPos.SIZE][ChunkPos.SIZE];

    public Chunk(ChunkPos pos) {
        this.pos = pos;
    }

    public ChunkPos getPos() {
        return pos;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTileAt(int x, int y, Tile tile) {
        tiles[x][y] = tile;
    }

    public Tile getTileAt(int x, int y) {
        return Objects.requireNonNullElse(tiles[x][y], Tiles.PLANKS);
    }
}
