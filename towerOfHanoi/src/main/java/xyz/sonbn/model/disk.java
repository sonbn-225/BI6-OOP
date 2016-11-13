package xyz.sonbn.model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by SonBN on 10-Nov-16.
 */
public class disk {
    private int numberOfTower, radius, totalDisk;
    private Color color;
    private Rectangle2D.Double state;

    public disk(int numberOfTower, int radius, int totalDisk){
        this.numberOfTower = numberOfTower;
        this.radius = radius;
        this.color = Color.getHSBColor(1.0f * radius/totalDisk, 0.7f, 0.7f);
        this.totalDisk = totalDisk;
        this.state = null;
    }

    public int getNumberOfTower() {
        return numberOfTower;
    }

    public void setNumberOfTower(int numberOfTower) {
        this.numberOfTower = numberOfTower;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getTotalDisk() {
        return totalDisk;
    }

    public void setTotalDisk(int totalDisk) {
        this.totalDisk = totalDisk;
    }

    public Rectangle2D.Double getState() {
        return state;
    }

    public void setState(Rectangle2D.Double state) {
        this.state = state;
    }
}
