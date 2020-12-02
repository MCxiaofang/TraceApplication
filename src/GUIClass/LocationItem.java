package GUIClass;

import javax.swing.*;
import java.util.Arrays;

public class LocationItem {
    public String []location=new String[9];
    public int []flag=new int[9];
    public int sup=0;
    public LocationItem(){

        location[0]=new String("北京工业大学");
        location[1]=new String("大洋路批发市场");
        location[2]=new String("欢乐谷景区");
        location[3]=new String("白鹿公园");
        location[4]=new String("北京东站");
        location[5]=new String("北京富力万丽酒店");
        location[6]=new String("金源公寓");
        location[7]=new String("中国传媒大学");
        location[8]=new String("潘家园大厦");
        Arrays.fill(flag, 0);
    }
    public void init(JComboBox<String> cb){
        for(int i=0;i<flag.length;i++){
            if(flag[i]==0){
                cb.addItem(location[i]);
            }
        }
    }

    public int flagfix(String str){
        for(int i=0;i<flag.length;i++){
            if(str.equals(location[i])){
                flag[i]=1;
                return i;
            }
        }
        System.out.println("error");
        return -1;
    }

    public void reinit(){
        Arrays.fill(flag, 0);
    }
}
