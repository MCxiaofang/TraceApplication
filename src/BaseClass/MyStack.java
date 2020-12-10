package BaseClass;


public class MyStack<T> {

    private T data;

    private MyStack<T> next;


    public MyStack(T data, MyStack<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public MyStack<T> getNext() {
        return next;
    }

    public void setNext(MyStack<T> next) {
        this.next = next;
    }
}