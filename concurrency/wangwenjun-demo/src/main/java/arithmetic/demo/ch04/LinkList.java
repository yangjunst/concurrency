package arithmetic.demo.ch04;
/*
 * 链表，相当于火车
 */
public class LinkList {
	//头结点
	private Node first;

	public LinkList() {
		first = null;
	}
	public static void main(String[] args) {
		LinkList linkList = new LinkList();
		linkList.insertFirst(34);
		linkList.insertFirst(23);
		linkList.insertFirst(12);
		linkList.insertFirst(0);
		linkList.insertFirst(-1);

		System.out.println(linkList.findByVal(-11));
//		linkList.deleteFirst();
//		linkList.display();
//
//		Node node = linkList.find(23);
//		node.display();

//		Node node1 = linkList.delete(0);
//		node1.display();
//		System.out.println();
//		linkList.display();
	}
	/**
	 * 插入一个结点，在头结点后进行插入
	 */
	public void insertFirst(long value) {
		Node node = new Node(value);
		node.next = first;
		first = node;
	}

	/**
	 * 删除一个结点，在头结点后进行删除
	 */
	public Node deleteFirst() {
		Node tmp = first;
		first = tmp.next;
		return tmp;
	}

	/**
	 * 显示方法
	 */
	public void display() {
		Node current = first;
		while(current != null) {
			current.display();
			current = current.next;
		}
		System.out.println();
	}
public Node findByVal(long val){
		Node current=first;
		while (current!=null){
			if(current.data==val){
				return current;
			}
			current=current.next;
		}
		return null;
}

public Node deleteByVal(long val){
		Node current=first;
		Node previous=null;
		while (current!=null){
			if(current.data==val){
				break;
			}
			previous=current;
			current=current.next;
		}

		if(current==first){
			first=first.next;
		}else if(current!=null) {
			previous.next=current.next;
		}
		return current;
}
	/**
	 * 查找方法
	 */
	public Node find(long value) {
		Node current = first;
		while(current.data != value) {
			if(current.next == null) {
				return null;
			}
			current = current.next;
		}
		return current;
	}

	/**
	 * 删除方法，根据数据域来进行删除
	 */
/* public Node delete(long value) {
		Node current = first;
		Node previous = first;
		while(current.data != value) {
			if(current.next == null) {
				return null;
			}
			previous = current;
			current = current.next;
		}

		if(current == first) {
			first = first.next;
		} else {
			previous.next = current.next;
		}
		return current;

	}*/




}
