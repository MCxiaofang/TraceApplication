package BaseClass;

public class RB_Node{
    private static final boolean RED=false;
    private static final boolean BLACK=true;

    public boolean color;
    public long key;
    public long max;
    public TPerson data;
    public RB_Node parent;
    public RB_Node leftChild;
    public RB_Node rightChild;
    public RB_Node next=null;

    public RB_Node(){
        this.data = null;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public RB_Node(Boolean color, long key, long max,TPerson data, RB_Node parent, RB_Node leftChild, RB_Node rightChild) {
        this.color = color;
        this.key = key;
        this.max = max;
        this.data = data;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public void setNext(RB_Node next){
        this.next=next;
    }

    public String toString(){
        String str="";
        RB_Node temp=this;
        do{
            str+=temp.data.toString();
            str+="\n";
            temp=temp.next;
        }while(temp.next!=null);
        return str;
    }

    public String jsString(){
        String str="";
        RB_Node temp=this;
        do{
            if(!temp.data.isProtect){
                str+=temp.data.jsString();
                str+=",";
                temp=temp.next;
            }
            else{
                temp=temp.next;
            }
        }while(temp!=null);
        return str;
    }
}