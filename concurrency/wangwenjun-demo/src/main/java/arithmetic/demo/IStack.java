package arithmetic.demo;

/********************************************
 * 文件名称: IStack.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/7 12:07
 *********************************************/
public class IStack {
    private int[] arr;
    private int top;

    public IStack() {
        this.arr=new int[10];
        this.top = -1;
    }

    public IStack(int size) {
        this.arr=new int[size];
        this.top = -1;
    }

    public void push(int val){
        arr[++top]=val;
    }

    public int peek(){
        return arr[top];
    }

    public int pop(){
        return arr[top--];
    }

    public boolean isEmpty(){
        return top==-1;
    }

    public boolean isFull(){
        return top==arr.length-1;
    }

}
