package GUIClass;

import BaseClass.*;
import StaticClass.LoadMessage;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public SearchPanel searchPanel;
    public InsertPanel insertPanel;
    public ChoosePanel choosePanel;
    public DetailMessage detailMessage;
    public MultiPersonPanel multiPersonPanel;
    public JTabbedPane jtp;
    public RB_Tree []traceTree=new RB_Tree[9];
    public ST_RB_Tree personTree=new ST_RB_Tree();
    public String html;

    public MainFrame() {
        this.html=MainMap.readToString("index.html");
        /* Initializes the data tree and load the data */
        for(int i=0;i<traceTree.length;i++){
            traceTree[i]=new RB_Tree();
            LoadMessage.load("Data//"+LocationItem.location[i]+".txt",traceTree[i]);
            //维护整个树的max值
            traceTree[i].maintainMax(traceTree[i].getRoot());
        }
        /* Initializes the people tree and load the data */
        for(int i=0;i<9;i++){
            LoadMessage.loadPerson("Data//"+LocationItem.location[i]+".txt",personTree,i);
        }

        /* Initializes the panel */
        searchPanel=new SearchPanel(this);
        insertPanel=new InsertPanel(this);
        choosePanel=new ChoosePanel(this);
        detailMessage=new DetailMessage(this);
        multiPersonPanel=new MultiPersonPanel(this);

        /* Initializes the TabbedPane */
        jtp=new JTabbedPane(JTabbedPane.TOP);
        jtp.add("简化搜索", choosePanel);
        jtp.add("录入数据", searchPanel);
        jtp.add("添加人员", insertPanel);
        jtp.add("详细信息", detailMessage);
        jtp.add("多人同时", multiPersonPanel);
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
