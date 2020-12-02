package StaticClass;

import BaseClass.RB_Tree;
import BaseClass.TPerson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LoadMessage {
    static String encoding = "utf-8";
    static File file;
    static InputStreamReader read;
    static BufferedReader bufferedReader;
    static String lineTxt;
    static StringBuilder str;
    static String[] numbersArray;
    public static void load(String filePath, RB_Tree tree) {
        try {
            System.gc();
            file = new File(filePath);
            if (file.isFile() && file.exists()) {
                read = new InputStreamReader(new FileInputStream(file), encoding);
                bufferedReader = new BufferedReader(read);

                lineTxt = null;
                str = new StringBuilder();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    str.append(lineTxt);
                }
                numbersArray = str.toString().split("@");

                for (int i = 0; i < numbersArray.length; i = i + 10) {
                    tree.insert(new TPerson(numbersArray[i], numbersArray[i + 1], numbersArray[i + 2], numbersArray[i + 3],
                            numbersArray[i + 4], numbersArray[i + 5], numbersArray[i + 6],
                            numbersArray[i + 8], numbersArray[i + 9], numbersArray[i + 7]));
                }
            } else {
                System.out.println("Error for finding file");
            }
        } catch (Exception e) {
            System.out.println("Error for load message");
            e.printStackTrace();
        }
    }
}
