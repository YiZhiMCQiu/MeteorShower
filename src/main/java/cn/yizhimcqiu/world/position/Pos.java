package cn.yizhimcqiu.world.position;

public class Pos {
    public int x;
    public int y;
    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public float distanceTo(Pos pos) {
        return (float) Math.sqrt(Math.pow(pos.x - x, 2) + Math.pow(pos.y - y, 2));
    }
    public Pos add(Pos pos) {
        return new Pos(x + pos.x, y + pos.y);
    }
    public Pos subtract(Pos pos) {
        return new Pos(x - pos.x, y - pos.y);
    }
    public Pos multiply(int i) {
        return new Pos(x * i, y * i);
    }
    public Pos divide(int i) {
        return new Pos(x / i, y / i);
    }
    public Pos copy() {
        return new Pos(x, y);
    }
    public Pos correction() {
        Pos res = this.copy();
        if (res.y < 0) {
            res.y = 0;
        }
        return res;
    }
    public Pos correction(Region region) {
        if (!region.contains(this)) {
            return region.center();
        }
        return this.correction();
    }
}
