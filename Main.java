package Assign_2;

import BasicIO.*;
import static BasicIO.Formats.*;

/** This class ...
  *
  * @author Aman Braich
  * @version 1.0 Feb 21st, 2019  
  * Main Class to access and sort the word gotten from the other classes
  */
public class Main {
  private Node            firstList;
  private ASCIIDisplayer  displayer = new ASCIIDisplayer(); 
  WordReader              wordReader;
  
  public Main ( ) {  
    wordReader = new WordReader();
    firstList = null;
    fileReader();
    Node j = secondListSorted();
    listToDisplayer(j);    
    displayer.close();
    wordReader.close();
  }//Main
  
  /* This meathod will use the wordReader class to read the next word and apply the
   * checkDuplicateList to check for duplicate word in firstList while ignoring case sensitivity 
   * the procceds to add to Node a lsit and adds to the frequency of the word */
  private void fileReader(){
    for ( ; ; ) {
      if (wordReader.isEOF()){
        break;
      }
      String string = wordReader.readWord();
      Word wordFreq = new Word();
      Node j = checkDuplicateList(string);
      if (j == null){
        wordFreq.words = string;
        wordFreq.freqency = 1;
        wordToList(wordFreq);
      }
      else {
        j.item.freqency++;
      }
    }
  }//fileReader
  
  
  //meathod creates new node and adds word
  private void wordToList (Word i){
    firstList = new Node(i, firstList);     
  };
  
  
  /* This meathod checks if there are any nodes containg the given 
   * word and will return it.
   * If a node is not found it will return null.
   * also uses equalsIgnoreCase meathod. */
  private Node checkDuplicateList(String given){
    Node  j = firstList;
    while (j != null){
      if (given.equalsIgnoreCase(j.item.words)){
        return j;
      }
      j = j.next;
      if (j == null){
        break;
      }
    }
    return null;
  };//checkDuplicateList
  
  
  //This meathod displays the firstList in the Displayer with word and frequency
  public void listToDisplayer (Node firstList){
    Node  j = firstList;
    while (j != null){
      displayer.writeInt(j.item.freqency);
      displayer.writeString(j.item.words);
      displayer.newLine();
      j = j.next;
    };
    displayer.newLine();
  };//listToDisplayer 
  
  /* This meathod sorts the words for most to least based off their frequencies  
   * it does this by making a new node list from the old */
  private Node secondListSorted (){
    Node newWordAdded = null;
    Word word = null;
    Node topPointer = null; //will point at the begining of the new node list
    for ( ;; ) {        //infinite loop
      word = mostFrequent();
      if (word == null){
        break;          // breaks if no more words are found
      } 
      else {
        if (newWordAdded == null){  //this will only occure on the first run through of the forever loop to create a new Node list
          newWordAdded = new Node (word, null);
          topPointer = newWordAdded;    
        } 
        else {
          newWordAdded.next = new Node (word, null);
          newWordAdded = newWordAdded.next;         
        }
      }
    }
    return topPointer;
  };//secondListSorted
  
  
  //This Meathod looks for the word that is the most frequent in the node list returns it, otherwise null
  private Word mostFrequent (){   
    Node j = firstList;
    Node k = null;
    int mostRepeated = 0;
    while (j != null){
      if ( j.item.freqency > mostRepeated ){
        mostRepeated = j.item.freqency;
        k = j;
      }
      j = j.next;     
    }
    if(k == null){
      return null;
    }
    else {
      Word wordMostFChanged = new Word();
      wordMostFChanged.words = k.item.words;
      wordMostFChanged.freqency = k.item.freqency;
      k.item.freqency = -1;
      return wordMostFChanged;
    }
  };//mostFrequent
  
  public static void main ( String[] args ) { Main z = new Main(); };
}//Main