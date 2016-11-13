package xyz.sonbn.model;

import java.util.Observable;
import java.util.Stack;

/**
 * Created by SonBN on 10-Nov-16.
 */
public class tower extends Observable{
    private int number;
    private Stack<disk> diskStack;


    public tower(int number){
        this.number = number;
        this.diskStack = new Stack<disk>();
    }

    public int getNumber(){
        return this.number;
    }

    public Stack<disk> getDiskStack() {
        return diskStack;
    }

    public void pushDisk(disk newDisk){
        disk disk = new disk(newDisk.getNumberOfTower(), newDisk.getRadius(), newDisk.getTotalDisk());
        this.diskStack.push(disk);
    }

    public void removeAllDisk(){
        this.diskStack.removeAllElements();
    }
}
