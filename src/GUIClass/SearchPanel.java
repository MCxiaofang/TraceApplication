package GUIClass;

import BaseClass.RB_Node;
import StaticClass.CSS;
import StaticClass.Date;
import StaticClass.SaveMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

public class SearchPanel extends JPanel implements ActionListener {
    public MainFrame frame;
    public JPanel[] posjp = new JPanel[11];
    public LocationItem city = new LocationItem();
    /* function button */
    public JButton confirm, clear;
    public JButton newItem;
    public JButton visual;
    public ImageIcon newItemIcon;
    /* Dynamic display */
    public JLabel[] arrLoc = new JLabel[9];
    public JLabel[] arrTime = new JLabel[9];
    public JLabel[] outTime = new JLabel[9];
    /* Dynamic input */
    public JComboBox<String>[] cb = new JComboBox[9];
    public JTextField[] arrTimeIn = new JTextField[9];
    public JTextField[] outTimeIn = new JTextField[9];
    /* Dynamic key flag*/
    public int dynamicFlag;
    public int length;

    public SearchPanel(MainFrame frame) {
        this.frame = frame;
        this.dynamicFlag = 0;
        this.length = 0;
        this.setLayout(new GridLayout(11, 1));

        /* Initializes the panel for location */
        for (int i = 0; i < posjp.length; i++) {
            posjp[i] = new JPanel();
            //posjp[i].setBackground(new Color(i*2,i*5,i*10));
            posjp[i].setBackground(Color.white);
            posjp[i].setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
            this.add(posjp[i]);
        }
        init0();
        init1();
        init2();
    }

    public void init0() {
        /* Initializes add new item button */
        newItemIcon = new ImageIcon(new ImageIcon("src/material/add.png").getImage().getScaledInstance(24, 24, 0));
        newItem = new JButton(newItemIcon);
        newItem.setPreferredSize(new Dimension(40, 40));
        newItem.setBackground(Color.WHITE);
        newItem.setBorderPainted(false);
        newItem.addActionListener(this);
        posjp[1].add(newItem);
    }

    public void init1() {
        /* Initializes first input location */
        cb[0] = new JComboBox<String>();
        city.init(cb[0]);
        arrTimeIn[0] = new JTextField(15);
        outTimeIn[0] = new JTextField(15);
        posjp[0].add(cb[0]);
        posjp[0].add(arrTimeIn[0]);
        posjp[0].add(outTimeIn[0]);
    }

    public void init2() {
        /* Initializes the confirm and clean button */
        confirm = new JButton("确认");
        clear = new JButton("清空");
        visual = new JButton("地图");
        CSS.CSS_BUTTON_1(confirm);
        CSS.CSS_BUTTON_1(clear);
        CSS.CSS_BUTTON_1(visual);

        confirm.addActionListener(this);
        clear.addActionListener(this);
        visual.addActionListener(this);
        posjp[10].setLayout(new FlowLayout(FlowLayout.CENTER, 30, 7)); /* hgap:Horizontal vgap: vertica */
        posjp[10].add(confirm);
        posjp[10].add(clear);
        posjp[10].add(visual);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frame.searchPanel.newItem) {
            if (check() && dynamicFlag != 9) {
                showLast();
                addNewLine();
            } else {
                JOptionPane.showMessageDialog(null,
                        "输入格式有误！", "提示", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == frame.searchPanel.confirm) {
            StringBuilder order = new StringBuilder(new String("["));
            String[] message = new String[9];
            long[] arrTime = new long[9];

            for (int i = 0; i < 9; i++) {
                message[i] = new String("");
            }
            BubbleSort(cb, arrTimeIn, outTimeIn);
            SaveMessage.deleteDir("Result");
            int fixI = 0;
            for (int i = 0; i < dynamicFlag; i++) {
                RB_Node result;
                int locate = city.flagfix(cb[i].getSelectedItem().toString());
                result = frame.traceTree[locate].search(
                        Date.parseInt(arrTimeIn[i].getText()), Date.parseInt(outTimeIn[i].getText()),
                        frame.traceTree[locate].getRoot());

                if (result != null) {
                    arrTime[i - fixI] = Date.parseInt(arrTimeIn[i].getText());
                    order.append(locate).append(",");
                    message[i - fixI] = result.jsString();
                    SaveMessage.save("Result//" + SaveMessage.locName[locate] + ".txt", result);
                } else {
                    fixI++;
                }
            }
            order.append("]");


            String temphtml = MainMap.readToString("TraceMap.html");
            for (int i = 0; i < 9; i++) {
                temphtml = temphtml.replaceFirst("points18061119" + i, message[i]);
            }
            temphtml = temphtml.replaceFirst("order18061119", order.toString());
            frame.html = temphtml;
            SaveMessage.SaveHtml(frame.html);
        } else if (e.getSource() == frame.searchPanel.clear) {
            for (int i = 0; i < posjp.length; i++) {
                posjp[i].removeAll();
                posjp[i].setVisible(false);
                posjp[i].setVisible(true);
            }
            dynamicFlag = 0;
            city.reinit();
            init0();
            init1();
            init2();
        } else if (e.getSource() == frame.searchPanel.visual) {
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

    public boolean check() {
        return true;
    }

    public void showLast() {
        arrLoc[dynamicFlag] = new JLabel("地点：" + cb[dynamicFlag].getSelectedItem().toString());
        arrTime[dynamicFlag] = new JLabel("到达时间" + arrTimeIn[dynamicFlag].getText());
        outTime[dynamicFlag] = new JLabel("离开时间" + outTimeIn[dynamicFlag].getText());
        city.flagfix(cb[dynamicFlag].getSelectedItem().toString());
        posjp[dynamicFlag].removeAll();
        posjp[dynamicFlag].setVisible(false);
        posjp[dynamicFlag].add(arrLoc[dynamicFlag]);
        posjp[dynamicFlag].add(arrTime[dynamicFlag]);
        posjp[dynamicFlag].add(outTime[dynamicFlag]);
        posjp[dynamicFlag].setVisible(true);
    }

    public void addNewLine() {
        dynamicFlag++;
        if (dynamicFlag == 9) {
            posjp[dynamicFlag].removeAll();
            posjp[dynamicFlag].setVisible(false);
            posjp[dynamicFlag].setVisible(true);
            return;
        }
        cb[dynamicFlag] = new JComboBox<String>();
        city.init(cb[dynamicFlag]);
        arrTimeIn[dynamicFlag] = new JTextField(15);
        outTimeIn[dynamicFlag] = new JTextField(15);
        posjp[dynamicFlag].removeAll();
        posjp[dynamicFlag].setVisible(false);
        posjp[dynamicFlag + 1].add(newItem);
        posjp[dynamicFlag].add(cb[dynamicFlag]);
        posjp[dynamicFlag].add(arrTimeIn[dynamicFlag]);
        posjp[dynamicFlag].add(outTimeIn[dynamicFlag]);
        posjp[dynamicFlag].setVisible(true);
    }

    public void BubbleSort(JComboBox<String>[] cb, JTextField[] arrTimeIn, JTextField[] outTimeIn) {
        for (int i = 0; i < dynamicFlag - 1; i++) {
            for (int j = 0; j < dynamicFlag - 1 - i; j++) {
                if (Date.parseInt(arrTimeIn[j].getText()) > (Date.parseInt(arrTimeIn[j + 1].getText()))) {
                    JTextField temparr = arrTimeIn[j];
                    arrTimeIn[j]=arrTimeIn[j+1];
                    arrTimeIn[j+1]=temparr;
                    JTextField tempout = outTimeIn[j];
                    outTimeIn[j]=outTimeIn[j+1];
                    outTimeIn[j+1]=tempout;
                    JComboBox<String> temp = cb[j];
                    cb[j]=cb[j+1];
                    cb[j+1]=temp;
                }
            }
        }
    }
}
