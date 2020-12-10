package BaseClass;

public class ST_RB_Node {
    private static final boolean RED=false;
    private static final boolean BLACK=true;

    public boolean color;
    public long key;
    public TPerson data;
    public ST_RB_Node parent;
    public ST_RB_Node leftChild;
    public ST_RB_Node rightChild;
    public ST_RB_Node next=null;

    public ST_RB_Node(){
        this.data = null;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public ST_RB_Node(Boolean color, long key,TPerson data, ST_RB_Node parent, ST_RB_Node leftChild, ST_RB_Node rightChild) {
        this.color = color;
        this.key = key;
        this.data = data;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public void setNext(ST_RB_Node next){
        this.next=next;
    }

    public String toString(){
        String res="not implement";
        return res;
    }
}
