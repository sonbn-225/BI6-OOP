package xyz.sonbn.controller;

import xyz.sonbn.model.disk;
import xyz.sonbn.model.towersOfHanoi;
import xyz.sonbn.view.mainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by SonBN on 10-Nov-16.
 */
public class controller implements ActionListener, MouseMotionListener, MouseListener{
    private towersOfHanoi towersOfHanoi;
    private disk moveDisk;
    private mainView view;
    private int currentNumberOfDisk = 5;
    private double ax,ay,w,h;
    private Timer t = new Timer(500, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            view.repaint();
        }
    });

    public controller(towersOfHanoi towers, disk moveDisk, mainView mainView){
        this.towersOfHanoi = towers;
        this.moveDisk = moveDisk;
        System.out.println("First" + this.moveDisk);
        System.out.println("Second" + moveDisk);
        this.view = mainView;

    }

    public void newGame(){
        Object values[] = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        Object val = JOptionPane.showInputDialog(view, "No. Of Disks : ", "Input",
                JOptionPane.INFORMATION_MESSAGE, null, values, values[0]);
        if ((Integer) val != JOptionPane.CANCEL_OPTION){
            currentNumberOfDisk = (Integer) val;
            this.towersOfHanoi.resetDisk(currentNumberOfDisk);
            view.repaint();
        }
    }

    public void solveProblem(){
        this.towersOfHanoi.resetDisk(currentNumberOfDisk);
        t.start();
        try {
            move(currentNumberOfDisk, 0,1,2);
        } catch (InterruptedException e){

        }
    }

    public void move(int m, int tower0, int tower1, int tower2) throws InterruptedException{
        if (m>0){
            move(m-1, tower0, tower2, tower1);
            towersOfHanoi.getTower(tower2).getDiskStack().push(towersOfHanoi.getTower(tower0).getDiskStack().pop());
            view.getTowersPanel().repaint();
            Thread.sleep(500);
            System.out.println("Move disk " + m + " from tower " + tower0 + " to tower " + tower2);
            move(m-1, tower1, tower0, tower2);
        }
    }

    private int getCurrentTower(Point point){
        Rectangle2D.Double
                rA=new Rectangle2D.Double(),
                rB=new Rectangle2D.Double(),
                rC=new Rectangle2D.Double();

        rA.setFrame(0,0,view.getTowersPanel().getWidth()/3,view.getTowersPanel().getHeight());
        rB.setFrame(view.getTowersPanel().getWidth()/3,0,view.getTowersPanel().getWidth()/3,view.getTowersPanel().getHeight());
        rC.setFrame(2*view.getTowersPanel().getWidth()/3,0,view.getTowersPanel().getWidth()/3,view.getTowersPanel().getHeight());

        if(rA.contains(point))
            return 0;
        else if(rB.contains(point))
            return 1;
        else if(rC.contains(point))
            return 2;
        else
            return -1;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("Reset")){
            this.towersOfHanoi.resetDisk(currentNumberOfDisk);
            view.repaint();
        }
        if (e.getActionCommand().equals("Solve")){
            this.solveProblem();
        }
        if (e.getActionCommand().equals("New Game")){
            newGame();
        } else if (e.getActionCommand().equals("Best Game")){

        } else if (e.getActionCommand().equals("Exit")){
            System.exit(0);
        } else if (e.getActionCommand().equals("About")){
            JOptionPane.showMessageDialog(view,
                    "Coded by SonBN");
        }
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        Point position = e.getPoint();
        int numberOfTower = getCurrentTower(position);
        if (!towersOfHanoi.getTower(numberOfTower).getDiskStack().empty()){
            moveDisk.setDisk(towersOfHanoi.getTower(numberOfTower).getDiskStack().peek());
            if (moveDisk.getState().contains(position)){
                moveDisk.setDisk(towersOfHanoi.getTower(numberOfTower).getDiskStack().pop());
                ax = moveDisk.getState().getX();
                ay = moveDisk.getState().getY();
                w = position.getX() - ax;
                h = position.getY() - ay;
                moveDisk.setDraggable(1);
            } else {
                moveDisk.setState(null);
                moveDisk.setColor(new Color(227, 242, 253));
                moveDisk.setDraggable(0);
            }
        }
        System.out.println("Mouse Press" + moveDisk);
    }

    public void mouseReleased(MouseEvent e) {
        if (moveDisk.getState() != null && moveDisk.getDraggable() == 1){
            Point position = e.getPoint();
            System.out.println("Move Disk: " + moveDisk.getRadius());
            System.out.println("Test   "+moveDisk.getState());
            System.out.println("Color: " + moveDisk.getColor());
            //System.out.println(position);
            int numberOfTower = getCurrentTower(position);
            if (numberOfTower != -1){
                if (!towersOfHanoi.getTower(numberOfTower).getDiskStack().empty()){
                    if (towersOfHanoi.getTower(numberOfTower).getDiskStack().peek().getRadius() < moveDisk.getRadius()){
                        JOptionPane.showMessageDialog(view,"Wrong Move","Tower Of Hanoi",JOptionPane.ERROR_MESSAGE);
                        numberOfTower = getCurrentTower(new Point((int) ax, (int) ay));
                    }
                }
            }


            moveDisk.setNumberOfTower(numberOfTower);
            towersOfHanoi.getTower(numberOfTower).pushDisk(moveDisk);
            moveDisk.setRadius(0);
            moveDisk.setColor(new Color(227, 242, 253));
            moveDisk.setDraggable(0);
            view.repaint();
        }

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        int cx = e.getX();
        int cy = e.getY();
        if (moveDisk.getRadius() != 0 && moveDisk.getDraggable() == 1){
            moveDisk.setState(cx-w,cy-h,moveDisk.getState().getWidth(),moveDisk.getState().getHeight());
            view.repaint();
        }
    }

    public void mouseMoved(MouseEvent e) {

    }
}
