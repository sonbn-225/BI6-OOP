package xyz.sonbn.controller;

import xyz.sonbn.model.disk;
import xyz.sonbn.model.tower;
import xyz.sonbn.view.mainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SonBN on 10-Nov-16.
 */
public class controller implements ActionListener{
    private tower[] tower = new tower[3];
    private disk disk;
    private mainView view;
    private int currentNumberOfDisk;

    public void initTower(int numberOfTower){
        for (int i = 0; i < numberOfTower; i++) {
            tower[i] = new tower(i);
        }
    }

    public void initDisk(int numberOfDisk){
        currentNumberOfDisk = numberOfDisk;
        for (int i = 0; i < numberOfDisk; i++) {
            disk = new disk(0, i, numberOfDisk);
            tower[0].pushDisk(disk);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Reset")){
            initDisk(currentNumberOfDisk);
            initDisk(3);
        }
        if (e.getActionCommand().equals("Solve")){
            this.solveProblem();
        }
        if (e.getSource() == view.newGame){
            newGame();
        } else if (e.getSource() == view.bestGame){

        } else if (e.getSource() == view.exit){
            System.exit(0);
        } else if (e.getSource() == view.about){
            System.out.println("ABC");
            JOptionPane.showMessageDialog(view,
                    "Coded by SonBN");
        }
    }

    public void newGame(){
        Object values[] = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        Object val = JOptionPane.showInputDialog(view.frame, "No. Of Disks : ", "Input",
                JOptionPane.INFORMATION_MESSAGE, null, values, values[0]);
        if ((Integer) val != JOptionPane.CANCEL_OPTION){
            initTower(3);
            initDisk((Integer) val);
        }
    }

    public tower[] getTower(){
        return this.tower;
    }

    public void setView(mainView view){
        this.view = view;
    }

    public void solveProblem(){

    }
}
