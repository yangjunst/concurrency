package arithmetic.demo;

import arithmetic.demo.ch05.Node;

/********************************************
 * 文件名称: IDoubleLinkList.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/8 14:10
 *********************************************/
public class IDoubleLinkList {
    private Node first;
    private Node last;


    public void insertFirst(long val) {

        Node node=new Node(val);
        if(isEmpty()){
            last=node;
        }else{
            first.previous=node;
        }
        node.next=first;
        first=node;

    }

    public void insertLast(long val) {
        Node node=new Node(val);
        if(isEmpty()){
            first=node;
        }else {
            last.next=node;
            node.previous=last;
        }
        last=node;
    }

    public void deleteFirst() {
        if(first.next==null){
            last=null;
        }else{
            first.next.previous=null;
        }
        first=first.next;
    }

    public void deleteLast() {
        if(first.next==null){
            first=null;
        }else {
            last.previous.next=null;
        }
        last=last.previous;
    }
    private boolean isEmpty(){
        return first==null;
    }
}
