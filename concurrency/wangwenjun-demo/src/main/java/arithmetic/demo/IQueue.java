package arithmetic.demo;

/********************************************
 * 文件名称: IQueue.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/7 14:06
 *********************************************/
public class IQueue {
    private int[] arr;
    private int elements;
    private int front;
    private int end;


    public IQueue(int size) {
        this.arr=new int[size];
        end=-1;
    }

    public void insert(int val){
        elements++;
        this.arr[++end]=val;
    }

    public int peek(){
        return this.arr[front];
    }

    public int remove(){
        elements--;
        return this.arr[front++];
    }

    public boolean isEmpty(){
        return elements==0;
    }

    public boolean isFull(){
        return elements==arr.length;
    }

}
