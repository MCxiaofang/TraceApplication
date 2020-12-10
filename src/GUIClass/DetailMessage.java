package GUIClass;

import BaseClass.LinkStack;
import BaseClass.ST_RB_Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailMessage extends JPanel implements ActionListener {
    public MainFrame frame;
    public PersonButton[] personButton = new PersonButton[120];
    LinkStack<ST_RB_Node> stack;

    public DetailMessage(MainFrame frame) {
        this.frame = frame;
        this.setLayout(new GridLayout(17, 6));

        stack = new LinkStack<>();
        stack = frame.personTree.PreOrder(frame.personTree.getRoot());
        int i = 0;
        while (!stack.isEmpty()) {
            ST_RB_Node person = stack.pop();
            personButton[i] = new PersonButton(person.data.personName, person);
            personButton[i].addActionListener(this);
            this.add(personButton[i]);
            i++;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PersonButton search = (PersonButton) e.getSource();
        ST_RB_Node person = search.node;
        DetailPanel panel=new DetailPanel(person);
    }

    public ST_RB_Node BubbleSort(ST_RB_Node person) {
        ST_RB_Node bubble = person;
        ST_RB_Node head =new ST_RB_Node();
        ST_RB_Node before;
        int count = 0;
        head.next=person;
        while (person != null) {
            count++;
            person = person.next;
        }
        for (int i = 0; i < count - 1; i++) {
            before=head;
            bubble=head.next;
            for (int j = 0; j < count - i - 1; j++) {
                if (bubble.data.arrTime.getStamp() > bubble.next.data.arrTime.getStamp()) {
                    before.next=bubble.next;
                    bubble.next=bubble.next.next;
                    before.next.next=bubble;
                }
                before=before.next;
                bubble=before.next;
            }
        }
        return head.next;
    }
}
