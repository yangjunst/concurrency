package arithmetic.demo;

import arithmetic.demo.ch05.Node;

/********************************************
 * 文件名称: IFirstLastLinkList.java
 * 功能说明: 
 * 开发人员: 雪域青竹
 * 开发时间: 2021/4/8 12:36
 *********************************************/
public class IFirstLastLinkList {
    private Node first;
    private Node last;
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
     public void insertFirst(long val){
         Node node=new Node(val);
         if(isEmpty()){
             last=node;
         }
         node.next=first;
         first=node;
     }

     public void insertLast(long val){
         Node node=new Node(val);
         if(isEmpty()){
             first=node;
         }else{
             last.next=node;
         }
         last=node;
     }

     public Node deleteFirst(){
         Node tmp=first;
         if(first.next==null){
             last=null;
         }
         first=tmp.next;
         return tmp;
     }

     public Node deleteLast(){
         Node current=first;
         while (current!=null){
             if(current.next==last){
                 Node result=last;
                 current.next=null;
                 last=current;
                 return last;
             }
             current=current.next;
         }
         return null;
     }

     public Node delete(long val){
         Node current=first;
         Node  previous=null;
         while (current!=null){
             if(current.data==val){
                 break;
             }
             previous=current;
             current=current.next;
         }
         if(current==first){
             first=first.next;
         }else if(current!=null&&previous!=null){
             previous.next=current.next;
         }
         return current;
     }

    private boolean isEmpty(){
        return first==null;
    }
}
