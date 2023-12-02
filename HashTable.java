import java.util.ArrayList;

class Node<K,V> {
    K key;
    V value;
   Node<K,V> next;


    Node(K key,V value){
        this.key=key;
        this.value=value;
        this.next=null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getNext() {
        return next;
    }

    public void setNext(Node<K, V> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value=" + value +

                '}';
    }
}
class MyHashMap<K,V> {

    LinkedList1<K> linkedList1;

    MyHashMap(){
        this.linkedList1=new LinkedList1<>();
    }

    V get(K key){
        Node<K,V> newnode=(Node<K, V>) this.linkedList1.search(key);
       return  (newnode==null)?null:newnode.getValue();



    }

    void add(K key,V value){
        Node<K,V> newnode=(Node<K, V>) this.linkedList1.search(key);
        if(newnode==null){
            newnode=new Node<>(key,value);
            this.linkedList1.append(newnode);
        }
        else{
            newnode.setValue(value);
        }

    }

    @Override
    public String toString() {
        return "MyHashMap{" +
                "linkedList1=" + linkedList1 +
                '}';
    }
}
class MyLinkedHashMap<K,V>{
    final int numBuckets;
    ArrayList<LinkedList1<K>> myBucketArray;

    MyLinkedHashMap(){
        this.numBuckets=10;
        this.myBucketArray=new ArrayList<>(numBuckets);
        for (int i=0;i<numBuckets;i++){
            this.myBucketArray.add(null);
        }
    }

   int getBucketIndex(K key){
        int hashCode=Math.abs(key.hashCode());
        int index=hashCode%numBuckets;

        return index;
    }

    void add(K key ,V value){



        int index=this.getBucketIndex(key);
        LinkedList1<K> temp_list=this.myBucketArray.get(index);



        if(temp_list==null){

            temp_list=new LinkedList1<>();

            this.myBucketArray.add(index,temp_list);

        }


        Node<K,V> newnode=(Node<K,V> )temp_list.search(key);

        if(newnode==null){


            newnode=new Node<K,V>(key,value);
            temp_list.append(newnode);

        }
        else{
            newnode.setValue(value);
        }





    }

    public V get(K key){
        int index=this.getBucketIndex(key);
        LinkedList1<K> linkedList1=this.myBucketArray.get(index);
        if(linkedList1==null){

            return null;
        }

        Node<K,V> newnode= linkedList1.search(key);
        return (newnode==null)?null:newnode.getValue();
    }
    void remove(K key){
        int index=this.getBucketIndex(key);

           this.myBucketArray.remove(index);


    }


    @Override
    public String toString() {
        return "MyLinkedHashMap{" +
                "numBuckets=" + numBuckets +
                ", myBucketArray=" + myBucketArray +
                '}';
    }
}
class LinkedList1<K>{



    Node head;
    Node tail;
    LinkedList1(){
        this.head=null;
        this.tail=null;
    }

    void add(Node myNode){
        if(this.tail==null){
            this.tail=myNode;
        }

        if(this.head==null){
            this.head=myNode;
        }
        else{
            Node tempNode=this.head;
            this.head=myNode;
            this.head.setNext(tempNode);
        }


    }
    void append(Node myNode){
        if(this.head==null){
            this.head=myNode;
        }

        if(this.tail==null){
            this.tail=myNode;
        }
        else{
           this.tail.setNext(myNode);
           this.tail=myNode;
        }


    }


    Node search(K key){
        Node tempNode=head;
        while (tempNode!=null && tempNode.getNext()!=null){
            if(tempNode.getKey().equals(key)){
                return tempNode;
            }
            tempNode=tempNode.getNext();

        }
        return null;
    }

    @Override
    public String toString() {
        return "LinkedList1{" +
                "head=" + head +

                '}';
    }



}

public class HashTable {
    public static void main(String[] args){

        String line="To be or not to be";

        String[] arr=line.toLowerCase().split(" ");

        String line2="“Paranoids are not\n" +
                "paranoid because they are paranoid but\n" +
                "because they keep putting themselves\n" +
                "deliberately into paranoid avoidable\n" +
                "situations";
        String[] arr1=line2.toLowerCase().split(" ");



        MyLinkedHashMap<String,Integer> linkedHashMap=new MyLinkedHashMap();
        MyHashMap<String,Integer> hashMap1=new MyHashMap();


        for(String s:arr){
         Integer val= hashMap1.get(s);

           if(val==null){

               val=1;


           }
           else{
             val+=1;
           }
           hashMap1.add(s,val);


        }

        for(String s:arr1){
            Integer val= linkedHashMap.get(s);

            if(val==null){

                val=1;


            }
            else{
                val+=1;
            }
            linkedHashMap.add(s,val);


        }
        linkedHashMap.remove("are");




        System.out.println(hashMap1.toString());
        System.out.println(linkedHashMap.toString());




    }
}
