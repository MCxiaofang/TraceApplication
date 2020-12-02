package BaseClass;

import StaticClass.Date;

public class TPerson extends Person {
    public Date arrTime,outTime;
    public boolean isProtect;

    public TPerson(){
        this.personName="测试成功";
        this.arrTime=new Date("1970-1-1 08:00:00");
        this.outTime=new Date("1970-1-2 08:00:00");
    }
    public TPerson(String personID,String personPhone,String personName,String personLive,
                   String personWork,String personSex,String personAge,
                   String arrTime,String outTime,String isProtect){
        super(personID,personPhone,personName,personLive,personWork,personSex,Integer.parseInt(personAge));
        this.arrTime=new Date(arrTime);
        this.outTime=new Date(outTime);
        this.isProtect=(Integer.parseInt(isProtect)==1);
    }

    public long getMin(){
        return arrTime.getStamp();
    }

    public long getMax(){
        return outTime.getStamp();
    }

    public String toString(){
        return super.toString()+
                "到达时间："+arrTime.toString()+
                "\n离开时间："+outTime.toString()+
                "\n防护状况："+isProtect+
                "\n";
    }

    public String jsString(){
        return super.jsString()+
                "\",\""+arrTime.toString()+
                "\",\""+outTime.toString()+
                "\",\""+isProtect+"\"]";
    }
}
