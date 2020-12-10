package GUIClass;

import BaseClass.ST_RB_Node;

import javax.swing.*;
import java.awt.*;

public class DetailPanel extends JFrame {
    public static int xMove=0,yMove=0;
    public JLabel label;

    public DetailPanel(ST_RB_Node person){
        xMove=(xMove+20)%100;
        yMove=(yMove+20)%100;

        label=new JLabel(person.data.htmlString());
        label.setFont(new Font("宋体", Font.BOLD, 20));
        this.add(label);

        this.setTitle(person.data.personName);
        this.setSize(400,400);
        this.setLocation(850+xMove,270+yMove);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

    }
}
