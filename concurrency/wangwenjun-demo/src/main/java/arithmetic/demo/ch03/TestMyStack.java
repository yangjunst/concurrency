package arithmetic.demo.ch03;

import arithmetic.demo.IStack;

public class TestMyStack {
	public static void main(String[] args) {
		IStack ms = new IStack(4);
		ms.push(23);
		ms.push(12);
		ms.push(1);
		ms.push(90);
		System.out.println(ms.isEmpty());
		System.out.println(ms.isFull());
		
		System.out.println(ms.peek());
		System.out.println(ms.peek());
		
		while(!ms.isEmpty()) {
			System.out.print(ms.pop() + ",");
		}
		
		System.out.println(ms.isEmpty());
		System.out.println(ms.isFull());
	}
}
