package StaticClass;

import BaseClass.RB_Node;

import java.io.File;
import java.io.FileOutputStream;

public class SaveMessage {

    public static String[] locName={"北京工业大学","大洋路批发市场","欢乐谷景区","白鹿公园",
            "北京东站","北京富力万丽酒店","金源公寓","中国传媒大学","潘家园大厦"};
    public static boolean save(String filePath, RB_Node result){
        deleteDir("Result");
        FileOutputStream fileOutputStream = null;
        File file = new File(filePath);
        String resStr="";
        /*while(result!=null){
            resStr+=result.data.toString()+"\n";
            result=result.next;
        }*/
        resStr=result.jsString();
        try {
            if(file.exists()){
                //判断文件是否存在，如果不存在就新建一个txt
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(resStr.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    return true;
    }

    public static void SaveHtml(String message){
        FileOutputStream fileOutputStream = null;
        File file = new File("ResultMap.html");
        try {
            if(file.exists()){
                //判断文件是否存在，如果不存在就新建一个txt
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(message.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteDir(String absolutePath){
        File file=new File(absolutePath);
        if(!file.exists()){
            System.err.println("The dir are not exists");
            return false;
        }

        String []content=file.list();
        for(String name:content){
            File temp=new File(absolutePath,name);
            if(temp.isDirectory()){
                deleteDir(temp.getAbsolutePath());
            }
            else{
                if(!temp.delete()){
                    System.err.println("Failed to delete"+name);
                }
            }
        }
        return true;
    }
}
