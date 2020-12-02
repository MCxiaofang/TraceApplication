package GUIClass;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    public JButton []jb=new JButton[3];
    public JPanel []jp=new JPanel[5];
    public JTextField jtf;
    public JLabel jlb1,jlb2;
    public JPasswordField jpf;
    public LoginListener listener;

    //设定用户名和密码
    final String username = "赵云雷";
    final String password = "18061119";

    public Login(){
        this.init();
        this.setLayout(new GridLayout(5,1));  			//选择GridLayout布局管理器
        this.setTitle("疫情人员追踪系统");
        this.setSize(300,200);
        this.setLocation(600, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出
        this.setVisible(true);
        this.setResizable(true);

    }

    private void init() {
        /* Initializes button */
        this.listener = new LoginListener(this);
        this.jb[0] = new JButton("登录");
        this.jb[1] = new JButton("重置");
        this.jb[2] = new JButton("退出");
        for (JButton jButton : jb) {
            jButton.setForeground(Color.blue);
            jButton.addActionListener(this.listener);
        }

        for(int i=0;i<jp.length;i++){
            this.jp[i]=new JPanel();
            this.jp[i].setBackground(new Color(0,150,195));
        }
        this.jp[0].setPreferredSize(new Dimension(300,20));

        this.jlb1 = new JLabel("用户名：");
        this.jlb2 = new JLabel("密    码：");


        this.jtf = new JTextField(10);
        this.jpf = new JPasswordField(10);

        this.jp[1].add(jlb1);
        this.jp[1].add(jtf);

        this.jp[2].add(jlb2);
        this.jp[2].add(jpf);

        this.jp[3].add(jb[0]);
        this.jp[3].add(jb[1]);
        this.jp[3].add(jb[2]);

        for (JPanel jPanel : jp) {
            this.add(jPanel);
        }
    }

    public void clear()
    {
        this.jtf.setText("");
        this.jpf.setText("");
    }

}


