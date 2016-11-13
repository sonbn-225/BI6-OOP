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
    private towersOfHanoi tower;
    private disk moveDisk;
    private mainView view;
    private int currentNumberOfDisk = 5;
    private boolean draggable = false;
    private double ax,ay,w,h;

    public controller(towersOfHanoi towers, disk moveDisk, boolean draggable, mainView mainView){
        this.tower = towers;
        this.moveDisk = moveDisk;
        this.draggable = draggable;
        this.view = mainView;

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("Reset")){
            this.tower.resetDisk(currentNumberOfDisk);
            view.repaint();
            System.out.println("Reset " + this.tower);//đây ạ
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
            System.out.println("ABC");
            JOptionPane.showMessageDialog(view,
                    "Coded by SonBN");
        }
    }

    public void newGame(){
        Object values[] = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        Object val = JOptionPane.showInputDialog(view, "No. Of Disks : ", "Input",
                JOptionPane.INFORMATION_MESSAGE, null, values, values[0]);
        if ((Integer) val != JOptionPane.CANCEL_OPTION){
            currentNumberOfDisk = (Integer) val;
            this.tower.resetDisk(currentNumberOfDisk);
            view.repaint();
            System.out.println("New Game" + this.tower);
        }
    }

    public void solveProblem(){

    }

    private int getCurrentTower(Point point){
        Rectangle2D.Double
                rA=new Rectangle2D.Double(),
                rB=new Rectangle2D.Double(),
                rC=new Rectangle2D.Double();

        rA.setFrame(0,0,view.getMyPanel().getWidth()/3,view.getMyPanel().getHeight());
        rB.setFrame(view.getMyPanel().getWidth()/3,0,view.getMyPanel().getWidth()/3,view.getMyPanel().getHeight());
        rC.setFrame(2*view.getMyPanel().getWidth()/3,0,view.getMyPanel().getWidth()/3,view.getMyPanel().getHeight());

        if(rA.contains(point))
            return 0;
        else if(rB.contains(point))
            return 1;
        else if(rC.contains(point))
            return 2;
        else
            return -1;
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        Point position = e.getPoint();
        int numberOfTower = getCurrentTower(position);
        if (!tower.getTower(numberOfTower).getDiskStack().empty()){
            moveDisk = tower.getTower(numberOfTower).getDiskStack().peek();
            System.out.println("Test   "+moveDisk.getState());
            System.out.println(position);
            if (moveDisk.getState().contains(position)){
                moveDisk = tower.getTower(numberOfTower).getDiskStack().pop();
                ax = moveDisk.getState().getX();
                ay = moveDisk.getState().getY();
                w = position.getX() - ax;
                h = position.getY() - ay;
                draggable = true;
            } else {
                moveDisk.setState(null);
                moveDisk.setColor(new Color(227, 242, 253));
                draggable = false;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (moveDisk.getState() != null && draggable){
            Point position = e.getPoint();
            int numberOfTower = getCurrentTower(position);
            if (!tower.getTower(numberOfTower).getDiskStack().empty()){
                if (tower.getTower(numberOfTower).getDiskStack().peek().getRadius() < moveDisk.getRadius()){
                    JOptionPane.showMessageDialog(view,"Wrong Move","Tower Of Hanoi",JOptionPane.ERROR_MESSAGE);
                    numberOfTower = getCurrentTower(new Point((int) ax, (int) ay));
                }
            }

            moveDisk.setState(null);
            tower.getTower(numberOfTower).pushDisk(moveDisk);
            moveDisk.setRadius(0);
            moveDisk.setColor(new Color(227, 242, 253));
            draggable = false;
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
        if (moveDisk.getRadius() != 0 && draggable){
            System.out.println("Drag");
            moveDisk.setState(new Rectangle2D.Double(cx-w,cy-h,moveDisk.getState().getWidth(),moveDisk.getState().getHeight()));
            view.repaint();
        }
    }

    public void mouseMoved(MouseEvent e) {

    }
}
