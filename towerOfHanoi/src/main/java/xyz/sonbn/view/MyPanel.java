package xyz.sonbn.view;

import xyz.sonbn.model.disk;
import xyz.sonbn.model.tower;
import xyz.sonbn.model.towersOfHanoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by SonBN on 10-Nov-16.
 */

public class MyPanel extends JPanel {
    private towersOfHanoi towers;
    private disk moveDisk;
    private boolean draggable = false;

    public MyPanel(towersOfHanoi tower){
        this.towers = tower;
    }

    public void setTower(towersOfHanoi tower){
        this.towers = tower;
        System.out.println("My Panel" + tower);
    }

    public void setMoveDisk(disk moveDisk){
        this.moveDisk = moveDisk;
    }

    public void setDraggable(boolean draggable){
        this.draggable = draggable;
    }


    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(new Color(227, 242, 253));
        graphics2D.fillRect(0,0, getWidth(), getHeight());

        int holder_x = getWidth()/6;
        int holder_y2 = getHeight()*9/10;
        int holder_y1 = getHeight()/10;

        graphics2D.setColor(Color.black);
        graphics2D.setStroke(new BasicStroke(7));
        graphics2D.drawLine(0,holder_y2,getWidth(),holder_y2);

        graphics2D.setColor(moveDisk.getColor());
        if (draggable && moveDisk.getRadius() != 0){
            graphics2D.fill(moveDisk.getState());
        }

        for (int i = 0; i<3; i++){
            drawTower(graphics2D, holder_x, holder_y1, holder_y2, towers.getTower(i));
        }
    }

    private void drawTower(Graphics2D graphics2D, int x, int y1, int y2, tower tower){
        graphics2D.setStroke(new BasicStroke(7));
        graphics2D.setColor(Color.BLACK);
        switch (tower.getNumber()){
            case 0:
                graphics2D.drawLine(x, y1, x, y2);
                break;
            case 1:
                graphics2D.drawLine(3*x, y1, 3*x, y2);
                break;
            case 2:
                graphics2D.drawLine(5*x, y1, 5*x, y2);
                break;
        }
        System.out.println("Draw " + tower.getNumber() + "  " + tower.getDiskStack());
        for (int i = 0; i < tower.getDiskStack().size(); i++) {
            int number = tower.getDiskStack().size() - i -1;
            disk disk = tower.getDiskStack().get(number);
            graphics2D.setColor(disk.getColor());
            if (disk.getState() == null){
                Rectangle2D.Double state = new Rectangle2D.Double();
                int magicNumber = disk.getTotalDisk() + 6;
                int width = getWidth()/(magicNumber - disk.getRadius()),
                        height = getHeight()/(magicNumber+2);
                state.setFrame(x - width/2, y2 - (i+1)*height, width, height);
                disk.setState(state);
            }
            graphics2D.fill(disk.getState());
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000,600); // As suggested by camickr
    }
}
