package GUIClass;

import GUIClass.Login;

import java.awt.event.*;
import javax.swing.*;

public class LoginListener implements ActionListener {
    Login login;

    public LoginListener(Login login){
        this.login = login;
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "登录":
                this.teacherlogin();
                break;
            case "重置":
                this.login.clear();
                break;
            case "退出":
                this.login.dispose();
                break;
        }
    }

    protected void teacherlogin() {
        if(this.login.username.equals(this.login.jtf.getText()) && this.login.password.equals(String.valueOf(this.login.jpf.getPassword()))) {
            JOptionPane.showMessageDialog(null,"登录成功！","提示",JOptionPane.WARNING_MESSAGE);
            this.login.clear();
            this.login.dispose();
            new MainFrame();
        }
        else if(this.login.jtf.getText().isEmpty()&&String.valueOf(this.login.jpf.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null,"请输入用户名和密码！","提示",JOptionPane.WARNING_MESSAGE);
        }
        else if(this.login.jtf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,"请输入用户名！","提示",JOptionPane.WARNING_MESSAGE);
        }
        else if(String.valueOf(this.login.jpf.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null,"请输入密码！","提示",JOptionPane.WARNING_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null,"用户名或者密码错误！\n请重新输入","提示",JOptionPane.ERROR_MESSAGE);
            login.clear();
        }
    }
}