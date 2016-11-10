package xyz.sonbn;

import xyz.sonbn.controller.controller;
import xyz.sonbn.model.tower;
import xyz.sonbn.view.mainView;

/**
 * Created by SonBN on 10-Nov-16.
 */
public class main {
    public static void main(String[] args){
        mainView view = new mainView();

        controller controller = new controller();
        controller.setView(view);

        controller.newGame();

        for (int i = 0; i<3; i++){
            controller.getTower()[i].addObserver(view);
        }

        view.addController(controller);
    }
}
