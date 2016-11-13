package xyz.sonbn.view;

import xyz.sonbn.controller.controller;
import xyz.sonbn.model.disk;
import xyz.sonbn.model.tower;
import xyz.sonbn.model.towersOfHanoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by SonBN on 10-Nov-16.
 */
public class mainView extends JFrame implements Observer {
    public JMenuItem newGame, bestGame, exit, about;
    private JButton resetButton, solveButton;
    private JPanel panel, main;
    private MyPanel myPanel;
    private towersOfHanoi towers;
    private disk moveDisk;
    private boolean draggable;

    public mainView(towersOfHanoi towers, disk moveDisk, boolean draggable){
        super("Tower of Hanoi_Group 5");
        this.towers = towers;
        this.moveDisk = moveDisk;
        this.draggable = draggable;

        //Main menu bar
        JMenuBar menuBar = new JMenuBar();

        //Menu item
        newGame = new JMenuItem("New Game");
        bestGame = new JMenuItem("Best Game");
        exit = new JMenuItem("Exit");
        about = new JMenuItem("About");

        //Menu game option
        JMenu option = new JMenu("Game Option");
        option.add(newGame);
        option.add(bestGame);
        option.add(about);
        option.add(exit);

        //add menu game option to menu bar and set color of menu bar
        menuBar.add(option);
        menuBar.setBackground(new Color(144, 202, 249));

        // Add menu bar to frame
        setJMenuBar(menuBar);

        // Main panel that contain content
        panel = new JPanel();
        panel.setBackground(new Color(227, 242, 253));
        resetButton = new JButton("Reset");
        resetButton.setBackground(new Color(66, 165, 245));
        solveButton = new JButton("Solve");
        solveButton.setBackground(new Color(66, 165, 245));
        panel.add("Center", resetButton);
        panel.add("Center", solveButton);

        main = new JPanel();
        myPanel = new MyPanel(towers);
        main.add(myPanel);
        main.setBackground(new Color(227, 242, 253));
        main.repaint();

        add("South", panel);
        add("Center", main);
        addWindowListener(new CloseListener());
        setSize(1000,720);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);}

    public void update(Observable o, Object arg) {

    }

    public MyPanel getMyPanel(){
        return this.myPanel;
    }

    public void addController(controller controller){
        newGame.addActionListener(controller);
        resetButton.addActionListener(controller);
        solveButton.addActionListener(controller);
        bestGame.addActionListener(controller);
        about.addActionListener(controller);
        exit.addActionListener(controller);

        myPanel.addMouseListener(controller);
        myPanel.addMouseMotionListener(controller);
    }

    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        } //windowClosing()
    } //CloseListener

    public void paint(Graphics g) {
        myPanel.setTower(towers);
        myPanel.setMoveDisk(moveDisk);
        myPanel.setDraggable(draggable);
        System.out.println("MainView" + towers);
        System.out.println("Disk main view:" + towers.getTower(0).getDiskStack());
        super.paint(g); // This will paint the components.
    } // end method paint
}
