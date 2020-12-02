package GUIClass;

import BaseClass.RB_Tree;
import StaticClass.LoadMessage;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public SearchPanel searchPanel;
    public InsertPanel insertPanel;
    public JPanel jPanel1,jPanel2,jPanel3,jPanel4;
    public JTabbedPane jtp;
    public RB_Tree []traceTree=new RB_Tree[9];
    public String html;

    public MainFrame() {
        this.html=MainMap.readToString("index.html");
        /* Initializes the data tree and load the data */
        for(int i=0;i<traceTree.length;i++){
            traceTree[i]=new RB_Tree();
            LoadMessage.load("Data//"+i+".txt",traceTree[i]);
            //维护整个树的max值
            traceTree[i].maintainMax(traceTree[i].getRoot());
        }

        /* Initializes the panel */
        searchPanel=new SearchPanel(this);
        insertPanel=new InsertPanel(this);
        jPanel2=new JPanel();
        jPanel3=new JPanel();
        jPanel4=new JPanel();

        /* Initializes the TabbedPane */
        jtp=new JTabbedPane(JTabbedPane.TOP);
        jtp.add("录入数据", searchPanel);
        jtp.add("添加人员", jPanel1);
        jtp.add("一人多天", jPanel2);
        jtp.add("多人同时", jPanel3);
        jtp.add("简化搜索", jPanel4);
        this.add(this.jtp,BorderLayout.CENTER);

        /* Initialize the main framework */
        this.setTitle("星云雷疫情追踪系统");
        this.setSize(500,700);
        this.setLocation(500,120);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }
}
