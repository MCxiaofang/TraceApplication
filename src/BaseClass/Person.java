package BaseClass;

public class Person {
    public String personID;        //身份证号码
    public String personPhone;     //手机号码
    public String personName;      //姓名
    public String personLive;      //住址
    public String personWork;      //工作单位
    public String personSex;       //性别
    public int personAge;          //年龄

    public Person()
    {

    }
    public Person(String personID,String personPhone,String personName,String personLive,
            String personWork,String personSex,int personAge) {
        this.personID = personID;
        this.personPhone = personPhone;
        this.personName = personName;
        this.personLive = personLive;
        this.personWork = personWork;
        this.personSex = personSex;
        this.personAge = personAge;
    }

    public String toString()
    {
        return "身份证号码："+personID
                +"\n手机号码："+personPhone
                +"\n姓名："+personName
                +"\n住址："+personLive
                +"\n工作单位："+personWork
                +"\n性别："+personSex
                +"\n年龄："+personAge
                +"\n";
    }

    public String jsString(){
        return "[\""+personID
                +"\",\""+personPhone
                +"\",\""+personName
                +"\",\""+personLive
                +"\",\""+personWork
                +"\",\""+personSex
                +"\",\""+personAge;
    }



}
