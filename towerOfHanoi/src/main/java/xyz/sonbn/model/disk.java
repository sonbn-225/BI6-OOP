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
    private boolean draggable;

    public disk(int numberOfTower, int radius, int totalDisk){
        this.numberOfTower = numberOfTower;
        this.radius = radius;
        this.color = Color.getHSBColor(1.0f * radius/totalDisk, 0.7f, 0.7f);
        this.totalDisk = totalDisk;
        this.state = null;
    }

    public boolean getDraggable() {
        return draggable;
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    public void setDisk(disk disk){
        this.numberOfTower = disk.getNumberOfTower();
        this.radius = disk.getRadius();
        this.color = disk.getColor();
        this.totalDisk = disk.getTotalDisk();
        this.state = disk.getState();
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

    public Rectangle2D.Double getState() {
        return state;
    }

    public void setState(double x, double y, double w, double h) {
        this.state.setFrame(x,y,w,h);
    }

    public void setState(Rectangle2D.Double state){
        this.state = state;
    }
}
