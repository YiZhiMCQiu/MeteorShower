package cn.yizhimcqiu.world.position;

public class Pos {
    public int x;
    public int y;
    public int z;
    public Pos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public float distanceTo(Pos pos) {
        return (float) Math.sqrt(Math.pow(pos.x - x, 2) + Math.pow(pos.y - y, 2) + Math.pow(pos.z - z, 2));
    }
    public Pos add(Pos pos) {
        return new Pos(x + pos.x, y + pos.y, z + pos.z);
    }
    public Pos subtract(Pos pos) {
        return new Pos(x - pos.x, y - pos.y, z - pos.z);
    }
    public Pos multiply(int i) {
        return new Pos(x * i, y * i, z * i);
    }
    public Pos divide(int i) {
        return new Pos(x / i, y / i, z / i);
    }
    public Pos copy() {
        return new Pos(x, y, z);
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
