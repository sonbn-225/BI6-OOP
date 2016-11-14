package xyz.sonbn.view;

import xyz.sonbn.controller.controller;
import xyz.sonbn.model.disk;
import xyz.sonbn.model.towersOfHanoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by SonBN on 10-Nov-16.
 */
public class mainView extends JFrame {
    private JMenuItem newGame, bestGame, exit, about;
    private JButton resetButton, solveButton;
    private JPanel buttonPanel, main;
    private towersPanel towersPanel;
    private towersOfHanoi towers;
    private disk moveDisk;

    public mainView(towersOfHanoi towers, disk moveDisk){
        super("Tower of Hanoi_Group 5");
        this.towers = towers;
        this.moveDisk = moveDisk;

        //Main menu bar
        JMenuBar menuBar = new JMenuBar();

        //Menu item
        newGame = new JMenuItem("New Game");
        bestGame = new JMenuItem("Best Game");
        exit = new JMenuItem("Exit");
        about = new JMenuItem("About");

        //Menu game option
        JMenu option = new JMenu("Option");
        option.add(newGame);
        option.add(bestGame);
        option.add(about);
        option.add(exit);

        //add menu game option to menu bar and set color of menu bar
        menuBar.add(option);
        menuBar.setBackground(new Color(144, 202, 249));

        // Add menu bar to frame
        setJMenuBar(menuBar);

        // Main buttonPanel that contain content
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(227, 242, 253));
        resetButton = new JButton("Reset");
        resetButton.setBackground(new Color(66, 165, 245));
        solveButton = new JButton("Solve");
        solveButton.setBackground(new Color(66, 165, 245));
        buttonPanel.add("Center", resetButton);
        buttonPanel.add("Center", solveButton);

        main = new JPanel();
        towersPanel = new towersPanel(towers);
        main.add(towersPanel);
        main.setBackground(new Color(227, 242, 253));

        add("South", buttonPanel);
        add("Center", main);
        addWindowListener(new CloseListener());
        setSize(1000,720);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        main.repaint();
    }

    public towersPanel getTowersPanel(){
        return this.towersPanel;
    }

    public void addController(controller controller){
        newGame.addActionListener(controller);
        resetButton.addActionListener(controller);
        solveButton.addActionListener(controller);
        bestGame.addActionListener(controller);
        about.addActionListener(controller);
        exit.addActionListener(controller);

        towersPanel.addMouseListener(controller);
        towersPanel.addMouseMotionListener(controller);
    }

    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        } //windowClosing()
    } //CloseListener


    public void paint(Graphics g) {
        towersPanel.setTower(towers);
        towersPanel.setMoveDisk(moveDisk);
        super.paint(g);
    }
}
