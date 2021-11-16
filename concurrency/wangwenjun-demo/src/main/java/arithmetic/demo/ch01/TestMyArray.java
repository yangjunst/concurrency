package arithmetic.demo.ch01;

public class TestMyArray {
    static int[] arr = {1, 4, 8, 10, 13, 17, 35, 67, 89};

    public static int search(int val) {
        int middle = 0;
        int low = 0;
        int high = arr.length-1;
        while (true) {
            middle = (low + high) / 2;
            if (arr[middle] == val) {
                return middle;
            } else if (low > high) {
                return -1;
            } else {
                if (arr[middle] > val) {
                    high = middle - 1;
                } else {
                    low = middle + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(search(89));
    }
}
