package arithmetic.demo;

import java.util.Random;

/********************************************
 * 文件名称: ISimpleSkipList.java
 * 功能说明:
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/13 17:04
 *********************************************/
public class ISimpleSkipList {
    private static final byte HEAD = (byte) -1;
    private static final byte TAIL = (byte) 1;
    private static final byte DATA = (byte) 0;
    private Node head = new Node(null, HEAD);//最高层head
    private Node tail = new Node(null, TAIL);//最高层tail
    private int height = 0;
    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {

        ISimpleSkipList list = new ISimpleSkipList();
        for (int i = 0; i < 100; i++) {
            list.insert(random.nextInt(1000));
        }

        list.display();
    }

    public void display() {
        Node headNode = head;
        Node current = headNode.right;
        for (int i = 0; i < height; i++) {
            current = headNode.right;
            while (current.type == DATA) {
                System.out.print(current.toString());
            }
            headNode = headNode.down;
            System.out.println();
        }
    }

    public Node find(int element) {
        Node headNode = head;
        Node current = headNode.right;
        for (int i = 0; i < height; i++) {
            current = headNode.right;
            while (current.type == DATA) {
                if (current.value == element) {
                    return current;
                } else if (current.value < element) {
                    current = current.right;
                }
            }
            headNode = headNode.down;
        }
        return current;
    }

    public void insert(int val) {
        Node node = new Node(val);
        Node headNode = head;
        for (int i = 0; i < height; i++) {
            Node current = headNode.right;
            while (current.type == DATA) {
                if (current.value >= val) {
                    node.right = current;
                    node.left = current.left;
                    current.left = node;
                    current.left.right = node;
                } else {
                    current = current.right;
                }
                if (random.nextDouble() < 0.5d) {

                }
            }
            headNode = headNode.down;

        }

    }

    private static class Node {
        public Node left, right, up, down;
        private Integer value;
        private byte type = 0;

        public Node(Integer value) {
            this(value, DATA);
        }

        public Node(Integer value, byte type) {
            this.value = value;
            this.type = type;
        }

        public String toString() {
            return "->" + this.value;
        }
    }
}