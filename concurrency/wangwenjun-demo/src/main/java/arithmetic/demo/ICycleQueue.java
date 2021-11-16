package arithmetic.demo;

/********************************************
 * 文件名称: ICycleQueue.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/7 14:35
 *********************************************/
public class ICycleQueue {
    private int[] arr;
    private int elements;
    private int front;
    private int end;

    public ICycleQueue(int size) {
        this.arr = new int[size];
        end=-1;
    }

    public ICycleQueue() {
        this.arr = new int[5];
        end=-1;
    }

    public void insert(int val){

        if(end==arr.length-1){
            end=-1;
        }
        arr[++end]=val;
        elements++;
    }

    public int remove(){
        if(front==arr.length){
            front=0;
        }
        elements--;
        return arr[front++];
    }

    public int peek(){
        return arr[front];
    }

    public boolean isEmpty(){
        return elements==0;
    }

    public boolean isFull(){
        return elements==arr.length;
    }
}

