package GUIClass;

import BaseClass.LinkStack;
import BaseClass.RB_Node;
import BaseClass.ST_RB_Node;
import StaticClass.Date;
import StaticClass.SaveMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ChoosePanel extends JPanel implements ActionListener {
    public MainFrame frame;
    public PersonButton[] personButton = new PersonButton[120];
    LinkStack<ST_RB_Node> stack;

    public ChoosePanel(MainFrame frame) {
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

        StringBuilder order = new StringBuilder(new String("["));
        String[] message = new String[9];

        for (int i = 0; i < 9; i++) {
            message[i] = new String("");
        }
        SaveMessage.deleteDir("Result");
        int myi = 0;
        person=BubbleSort(person);
        do {
            int building = person.data.building;
            RB_Node result = frame.traceTree[building].
                    search(person.data.arrTime.getStamp(), person.data.outTime.getStamp(), frame.traceTree[building].getRoot());

            if (result != null) {
                order.append(building).append(",");
                message[myi] = result.jsString();
                SaveMessage.save("Result//" + SaveMessage.locName[building] + ".txt", result);
            }
            person = person.next;
            myi++;
        } while (person.next != null);
        order.append("]");

        //replace message and order
        String temphtml = MainMap.readToString("TraceMap.html");
        for (int i = 0; i < 9; i++) {
            temphtml = temphtml.replaceFirst("points18061119" + i, message[i]);
        }
        temphtml = temphtml.replaceFirst("order18061119", order.toString());

        //save html
        frame.html = temphtml;
        SaveMessage.SaveHtml(frame.html);

        // start Map from WebBrowser
        File file = new File("ResultMap.html");
        Runtime ce = Runtime.getRuntime();
        System.out.println(file.getAbsolutePath());
        try {
            ce.exec("cmd /c start " + file.getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
