package GUIClass;

import BaseClass.Person;
import BaseClass.TPerson;
import StaticClass.CSS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InsertPanel extends JPanel implements ActionListener {
    static int sum_block =12;
    static String[] labelName={"地点","身份证号码","手机号码","姓名","住址","工作单位","性别","年龄","防护措施","到达时间","离开时间"};
    public MainFrame frame;
    public JPanel []posjp=new JPanel[sum_block ];
    public JLabel []jl=new JLabel[sum_block ];
    public JTextField[]jt=new JTextField[sum_block];
    public JButton submitButton;
    public JComboBox<String> cb;

    public InsertPanel(MainFrame frame){
        this.frame=frame;

        //set the layout and  JPanel
        for (int i = 0; i < posjp.length; i++) {
            posjp[i] = new JPanel();
            posjp[i].setBackground(Color.white);
            posjp[i].setLayout(new GridLayout(1,2,10,20));
            this.add(posjp[i]);
        }

        for(int i = 1; i < posjp.length-1; i++){
            jt[i]=new JTextField(15);
        }

        this.setLayout(new GridLayout(posjp.length, 1));
        for (int i = 0; i < posjp.length-1; i++) {
            JLabel jl=new JLabel(labelName[i]);
            jl.setHorizontalAlignment(4);
            posjp[i].add(jl);
            if(i!=0) posjp[i].add(jt[i]);
        }
        cb = new JComboBox<String>();
        LocationItem.load(cb);
        posjp[0].add(cb);

        submitButton=new JButton("添加");
        CSS.CSS_BUTTON_1(submitButton);
        submitButton.addActionListener(this);

        posjp[posjp.length-1].add(submitButton);
        posjp[posjp.length-1].setLayout(new FlowLayout(FlowLayout.CENTER, 30, 7));
        this.add(posjp[posjp.length-1]);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.traceTree[cb.getSelectedIndex()].insert(new TPerson(jt[1].getText(),jt[2].getText(),jt[3].getText(),
                jt[4].getText(),jt[5].getText(),jt[6].getText(),jt[7].getText(),jt[9].getText(),jt[10].getText(),jt[8].getText()));
        frame.traceTree[cb.getSelectedIndex()].maintainMax(frame.traceTree[cb.getSelectedIndex()].getRoot());
    }
}
