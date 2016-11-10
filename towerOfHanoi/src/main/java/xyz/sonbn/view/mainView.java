package xyz.sonbn.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by SonBN on 10-Nov-16.
 */
public class mainView extends JPanel implements ActionListener, MouseListener, MouseMotionListener, Observer {
    public JMenuItem newGame, bestGame, exit, about;
    public JFrame frame;
    private JButton resetButton, solveButton;

    public mainView(){
        frame = new JFrame("Tower of Hanoi_Group 5");
        JMenuBar menuBar = new JMenuBar();

        newGame = new JMenuItem("New Game");
        bestGame = new JMenuItem("Best Game");
        exit = new JMenuItem("Exit");
        about = new JMenuItem("About");

        JMenu option = new JMenu("Game Option");
        option.add(newGame);
        option.add(bestGame);
        option.add(about);
        option.add(exit);

        menuBar.add(option);
        menuBar.setBackground(new Color(144, 202, 249));

        newGame.addActionListener(this);
        exit.addActionListener(this);

        frame.setJMenuBar(menuBar);


        JPanel panel = new JPanel();
        panel.setBackground(new Color(227, 242, 253));
        resetButton = new JButton("Reset");
        resetButton.setBackground(new Color(66, 165, 245));
        solveButton = new JButton("Solve");
        solveButton.setBackground(new Color(66, 165, 245));
        panel.add("Center", resetButton);
        panel.add("Center", solveButton);

        JPanel main = new JPanel();
        MyPanel myPanel = new MyPanel();
        main.add(myPanel);
        main.setBackground(new Color(227, 242, 253));
        main.repaint();

        frame.add("South", panel);
        frame.add("Center", main);
        frame.addWindowListener(new CloseListener());
        frame.setSize(1000,720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addMouseMotionListener(this);
        addMouseListener(this);

        init(5);
    }

    private void init(int numberOfDisk){
    }

    public void update(Observable o, Object arg) {

    }

    public void addController(ActionListener controller){
        newGame.addActionListener(controller);
        resetButton.addActionListener(controller);
        solveButton.addActionListener(controller);
        bestGame.addActionListener(controller);
        about.addActionListener(controller);
        exit.addActionListener(controller);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {

        } else if (e.getSource() == exit)
            System.exit(0);
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e){

    }

    public void mouseEntered(MouseEvent e){

    }

    public void mouseClicked(MouseEvent e){

    }

    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        } //windowClosing()
    } //CloseListener
}
