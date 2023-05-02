package Assign_2;

import java.io.*;

class Node implements Serializable{
  //Declaring the item in the node will be the word given
  Word item;
  Node next;
  
  public Node(Word i, Node n){
    
    item = i;
    next = n;
  };//Node Constructer
  
}//Node Class