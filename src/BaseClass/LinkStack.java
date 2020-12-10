package BaseClass;

public class LinkStack<N> {

    private MyStack<N> head;

    private MyStack<N> tail;

    private Integer size=0;

    public MyStack<N> getHead() {
        return head;
    }

    public void setHead(MyStack<N> head) {
        this.head = head;
    }

    public MyStack<N> getTail() {
        return tail;
    }

    public void setTail(MyStack<N> tail) {
        this.tail = tail;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void push(N data){
        MyStack<N> node = new MyStack<>(data,null);
        if(isEmpty()){
            head = node;
            tail = node;
            size++;
        }else{
            node.setNext(head);
            head = node;
            size++;
        }
    }

    public N pop(){
        if(size>0){
            N outData = head.getData();
            head = head.getNext();
            return outData;
        }else{
            throw new RuntimeException("栈里无元素!");
        }
    }

    public boolean isEmpty(){
        if(head == null){
            return true;
        }
        return false;
    }
}