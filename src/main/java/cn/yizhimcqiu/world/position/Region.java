package cn.yizhimcqiu.world.position;

public class Region {
    public Pos pos1;
    public Pos pos2;
    public Region(Pos pos1, Pos pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
    }
    public Region(int x1, int y1, int x2, int y2) {
        this.pos1 = new Pos(x1, y1);
        this.pos2 = new Pos(x2, y2);
    }
    public boolean contains(Pos pos) {
        return pos.x >= pos1.x && pos.x <= pos2.x && pos.y >= pos1.y && pos.y <= pos2.y;
    }
    public Pos center() {
        return pos1.add(pos2).divide(2);
    }
}
