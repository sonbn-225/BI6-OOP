package xyz.sonbn;

import xyz.sonbn.controller.controller;
import xyz.sonbn.model.disk;
import xyz.sonbn.model.tower;
import xyz.sonbn.model.towersOfHanoi;
import xyz.sonbn.view.mainView;

/**
 * Created by SonBN on 10-Nov-16.
 */
public class main {
    public static void main(String[] args){
        towersOfHanoi tower = new towersOfHanoi(5);
        disk moveDisk = new disk(-1, 0, 5);
        boolean draggable = false;
        mainView view = new mainView(tower, moveDisk, draggable);
        controller controller = new controller(tower, moveDisk, draggable, view);

        //Tell view about controller
        view.addController(controller);

        controller.newGame();
    }
}
