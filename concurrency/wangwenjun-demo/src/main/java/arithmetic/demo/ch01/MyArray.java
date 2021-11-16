package arithmetic.demo.ch01;

public class MyArray {
    private long[] arr;
    //表示有效数据的长度
    private int elements;

    public MyArray() {
        arr = new long[50];
    }

    public MyArray(int maxsize) {
        arr = new long[maxsize];
    }

    /**
     * 添加数据
     */
    public void insert(long value) {
        arr[elements] = value;
        elements++;
        System.currentTimeMillis();
    }

    public void insert(long val,int index){
        if(index<0||index>arr.length){
            throw new ArrayIndexOutOfBoundsException(index);
        }

        if(elements>=arr.length){
            throw new RuntimeException("数组已满");
        }

        for(int i=elements;i>index;i--){
            arr[i]=arr[i-1];
        }
        arr[index]=val;
        elements++;
    }

    /**
     * 显示数据
     */
    public void display() {
        System.out.print("[");
        for(int i = 0; i < elements; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }

    /**
     * 查找数据
     */
    public int search(long value) {
        int i;
        for(i = 0; i < elements; i++) {
            if(value == arr[i]) {
                break;
            }
        }

        if(i == elements) {
            return -1;
        } else {
            return i;
        }

    }

    /**
     * 查找数据，根据索引来查
     */
    public long get(int index) {
        if(index >= elements || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return arr[index];
        }
    }

    /**
     * 删除数据
     */
    public void delete(int index) {
        if(index >= elements || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            for(int i = index; i < elements; i++) {
                arr[index] = arr[index + 1];
            }
            elements--;
        }
    }

    /**
     * 更新数据
     */
    public void change(int index, int newvalue) {
        if(index >= elements || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            arr[index] = newvalue;
        }
    }

    public static void main(String[] args) {
        MyArray array=new MyArray();
        array.insert(1);
        array.insert(11);
        array.insert(111);
        array.display();
        array.delete(0);
        array.display();
    }
}
