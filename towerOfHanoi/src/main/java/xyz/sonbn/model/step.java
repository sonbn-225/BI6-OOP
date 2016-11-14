package xyz.sonbn.model;

/**
 * Created by SonBN on 14-Nov-16.
 */
public class step {
    private int fromTower, toTower;

    public step(int fromTower, int toTower) {
        this.fromTower = fromTower;
        this.toTower = toTower;
    }

    public int getFromTower() {
        return fromTower;
    }

    public void setFromTower(int fromTower) {
        this.fromTower = fromTower;
    }

    public int getToTower() {
        return toTower;
    }

    public void setToTower(int toTower) {
        this.toTower = toTower;
    }
}
