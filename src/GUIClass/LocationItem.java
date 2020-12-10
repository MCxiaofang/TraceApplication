package GUIClass;

import javax.swing.*;
import java.util.Arrays;

public class LocationItem {
    public static String []location={"北京工业大学","大洋路批发市场","欢乐谷景区","白鹿公园","北京东站","北京富力万丽酒店","金源公寓","中国传媒大学","潘家园大厦"};
    public int []flag=new int[9];
    public int sup=0;
    public LocationItem(){
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

    public static void load(JComboBox<String> cb){
        for (String s : location) cb.addItem(s);

    }

    public void reinit(){
        Arrays.fill(flag, 0);
    }
}
