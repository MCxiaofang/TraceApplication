package StaticClass;

public class Debugger
{
    private static boolean EnableDebugger=true;

    private Debugger(){

    }
    public static void log(String s)
    {
        if(EnableDebugger){
            System.out.println(s);
        }
    }
    public static void log(int s)
    {
        if(EnableDebugger){
            System.out.println(s);
        }
    }
}

