package StaticClass;

import java.text.SimpleDateFormat;

public class Date {
    private String date;
    private long stamp;

    public Date() {
        date="";
        stamp=0;
    }

    public Date(String date){
        this.date=date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!"".equals(date)) {//时间不为空
            try {
                this.stamp = sdf.parse(date).getTime() / 1000;
            } catch (Exception e) {
                this.stamp=-1;
                System.out.println("时间参数有误！");
            }
        }
        else{
            stamp=-1;
        }
    }


    public static long parseInt(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long Stamp = 0;
        if (!"".equals(date)) {//时间不为空
            try {
                Stamp = sdf.parse(date).getTime() / 1000;
            } catch (Exception e) {
                System.out.println("时间参数有误！");
            }
        }
        return Stamp;
    }

    public static String parseString(long date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date*1000);
    }

    public long getStamp() {
        return stamp;
    }

    public String toString(){
        return date;
    }



}
