package xyz.sonbn.controller;

import xyz.sonbn.model.disk;
import xyz.sonbn.model.towersOfHanoi;
import xyz.sonbn.view.mainView;

import javax.swing.*;
import java.util.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

/**
 * Created by SonBN on 10-Nov-16.
 */
public class controller implements ActionListener, MouseMotionListener, MouseListener{
    private towersOfHanoi towersOfHanoi;
    private disk moveDisk;
    private mainView view;
    private int currentNumberOfDisk = 5;
    private double ax,ay,w,h;
    private ArrayList<Integer> steps = new ArrayList<Integer>();

    public controller(towersOfHanoi towers, disk moveDisk, mainView mainView){
        this.towersOfHanoi = towers;
        this.moveDisk = moveDisk;
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
        move(currentNumberOfDisk, 0,1,2);
        makeMove();
    }

    private void move(int numberOfDisk, int tower0, int tower1, int tower2){
        if (numberOfDisk>0){
            move(numberOfDisk-1, tower0, tower2, tower1);
            steps.add(tower0);
            steps.add(tower2);
            move(numberOfDisk-1, tower1, tower0, tower2);
        }
    }

    private void makeMove(){
        Timer timer = new Timer();
        for(int i=0;i<steps.size(); i+=2){
            final int towerSource = steps.get(i);
            final int towerDestination = steps.get(i+1);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    towersOfHanoi.getTower(towerDestination).pushDisk(towersOfHanoi.getTower(towerSource).getDiskStack().pop());
                    System.out.println("Move disk from tower " + towerSource + " to tower " + towerDestination);
                    view.getTowersPanel().repaint();
                }
            }, i*700);
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
        if (e.getActionCommand().equals("Reset")){
            this.towersOfHanoi.resetDisk(currentNumberOfDisk);
            view.repaint();
        }
        if (e.getActionCommand().equals("Solve")){
            this.solveProblem();
        }
        if (e.getActionCommand().equals("New Game")){
            newGame();
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
                moveDisk.setDraggable(true);
            } else {
                moveDisk.setState(null);
                moveDisk.setColor(new Color(227, 242, 253));
                moveDisk.setDraggable(false);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (moveDisk.getState() != null && moveDisk.getDraggable()){
            Point position = e.getPoint();
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
            moveDisk.setDraggable(false);
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
        if (moveDisk.getRadius() != 0 && moveDisk.getDraggable()){
            moveDisk.setState(cx-w,cy-h,moveDisk.getState().getWidth(),moveDisk.getState().getHeight());
            view.repaint();
        }
    }

    public void mouseMoved(MouseEvent e) {

    }
}
