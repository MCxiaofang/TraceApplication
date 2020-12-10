package GUIClass;

import BaseClass.ST_RB_Node;

import javax.swing.*;

public class PersonButton extends JButton {
    ST_RB_Node node;
    public PersonButton(String buttonName, ST_RB_Node node){
        super(buttonName);
        this.node=node;
    }
}
