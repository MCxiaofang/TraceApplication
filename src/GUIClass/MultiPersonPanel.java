package GUIClass;

import BaseClass.LinkStack;
import BaseClass.RB_Node;
import BaseClass.ST_RB_Node;
import StaticClass.SaveMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MultiPersonPanel extends JPanel implements ActionListener {
    public MainFrame frame;
    public PersonButton[] personButton = new PersonButton[120];
    public JPanel panel_person,panel_confirm;
    public JLabel label;
    public int select_num;
    LinkStack<ST_RB_Node> stack;

    public MultiPersonPanel(MainFrame frame){
        this.frame=frame;
        this.panel_person=new JPanel();
        this.panel_confirm=new JPanel();
        this.label=new JLabel("已选人数：0");
        this.select_num=0;

        panel_person.setLayout(new GridLayout(17,6));
        stack = new LinkStack<>();
        stack = frame.personTree.PreOrder(frame.personTree.getRoot());
        int i = 0;
        while (!stack.isEmpty()) {
            ST_RB_Node person = stack.pop();
            personButton[i] = new PersonButton(person.data.personName, person);
            personButton[i].addActionListener(this);
            panel_person.add(personButton[i]);
            i++;
        }

        label.setFont(new Font("宋体", Font.BOLD, 50));
        panel_confirm.add(label);
        this.add(panel_person);
        this.add(panel_confirm);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PersonButton search = (PersonButton) e.getSource();
        ST_RB_Node person = search.node;
        select_num++;

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
        if(select_num==1){
            label.setText("已选人数：1");
            String temphtml = MainMap.readToString("TraceMap_Multiple_person.html");
            for (int i = 0; i < 9; i++) {
                temphtml = temphtml.replaceFirst("points18061119" + i+"_1", message[i]);
            }
            temphtml = temphtml.replaceFirst("order18061119_1", order.toString());
            frame.html = temphtml;
            SaveMessage.SaveHtml(frame.html);
        }
        else{
            select_num=0;
            label.setText("已选人数：0");
            String temphtml = MainMap.readToString("ResultMap.html");
            for (int i = 0; i < 9; i++) {
                temphtml = temphtml.replaceFirst("points18061119" + i+"_2", message[i]);
            }
            temphtml = temphtml.replaceFirst("order18061119_2", order.toString());
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
