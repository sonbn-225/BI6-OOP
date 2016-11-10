package xyz.sonbn.model;

import java.awt.*;

/**
 * Created by SonBN on 10-Nov-16.
 */
public class disk {
    private int numberOfTower, radius;
    private Color color;

    public disk(int numberOfTower, int radius, int totalDisk){
        this.numberOfTower = numberOfTower;
        this.radius = radius;
        this.color = Color.getHSBColor(1.0f * radius/totalDisk, 0.7f, 0.7f);
    }
}
