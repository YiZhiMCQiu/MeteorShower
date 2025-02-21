package cn.yizhimcqiu.world.player;

import cn.yizhimcqiu.world.position.Pos;

public class Player {
    private final Pos pos;
    private Pos delta = new Pos(0, 0);

    public Player(Pos pos) {
        this.pos = pos;
    }

    public Pos getPos() {
        return pos;
    }

    public void tick() {
        this.pos.add(delta.divide(10));
        delta = delta.subtract(delta.divide(10));
    }
}
