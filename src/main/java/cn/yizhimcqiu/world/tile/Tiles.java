package cn.yizhimcqiu.world.tile;

import cn.yizhimcqiu.registry.Registries;
import cn.yizhimcqiu.registry.Registry;
import cn.yizhimcqiu.registry.ResourceLocator;

public class Tiles {
    public static final Tile AIR = register("air", new Tile());
    public static final Tile PLANKS = register("planks", new Tile());

    private static Tile register(String id, Tile tile) {
        return Registry.register(Registries.TILE, new ResourceLocator(id), tile);
    }

    public static void init() {
        throw new RuntimeException("Game Must Be Crashed!");
    }
}
