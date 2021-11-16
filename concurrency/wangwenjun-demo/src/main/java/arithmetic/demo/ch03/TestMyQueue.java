package arithmetic.demo.ch03;

import arithmetic.demo.ICycleQueue;

public class TestMyQueue {
	public static void main(String[] args) {
		ICycleQueue mq=new ICycleQueue(4);
		mq.insert(23);
		mq.insert(45);
		mq.insert(13);
		mq.insert(1);

		System.out.println(mq.isFull());
		System.out.println(mq.isEmpty());
		
		System.out.println(mq.peek());
		System.out.println(mq.peek());
		
		while (!mq.isEmpty()) {
			System.out.print(mq.remove() + " ");
		}
		System.out.println();

		mq.insert(45);
		mq.insert(13);
		mq.insert(1);

		System.out.println(mq.remove());
		System.out.println(mq.remove());
		System.out.println(mq.remove());
		System.out.println(mq.remove());
		System.out.println(mq.remove());
		System.out.println(mq.remove());
		System.out.println(mq.remove());
		System.out.println(mq.remove());
		System.out.println(mq.remove());
	}
}
