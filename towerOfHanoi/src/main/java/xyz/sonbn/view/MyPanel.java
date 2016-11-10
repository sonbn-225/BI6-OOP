package xyz.sonbn.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by SonBN on 10-Nov-16.
 */
public class MyPanel extends JPanel {
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(new Color(227, 242, 253));
        graphics2D.fillRect(0,0, getWidth(), getHeight());

        int holder_x=getWidth()/6;
        int holder_y1=getHeight()-10*20,holder_y2=getHeight()-20;

        graphics2D.setColor(Color.black);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawLine(holder_x,holder_y1,holder_x,holder_y2);
        graphics2D.drawLine(3*holder_x,holder_y1,3*holder_x,holder_y2);
        graphics2D.drawLine(5*holder_x,holder_y1,5*holder_x,holder_y2);
        graphics2D.drawLine(0,holder_y2,getWidth(),holder_y2);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000,600); // As suggested by camickr
    }
}
