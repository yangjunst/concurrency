package arithmetic.demo.ch05;

import arithmetic.demo.IFirstLastLinkList;

public class TestFirstLastLinkList {
	public static void main(String[] args) {
		
		IFirstLastLinkList fl = new IFirstLastLinkList();
//		fl.insertFirst(34);
//		fl.insertFirst(56);
//		fl.insertFirst(67);
//		fl.display();
//		
//		fl.deleteFirst();
//		fl.deleteFirst();
//		fl.display();
		
		fl.insertLast(56);
		fl.insertLast(90);
		fl.insertLast(12);
		fl.display();

		fl.deleteLast();
		fl.display();
	}
}
