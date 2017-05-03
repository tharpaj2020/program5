/* Aaron Tharp List class
 * 5/3/2017
 * Description: The purpose of this class is to provide the structure for adding and removing points to the animation path.
 * Acknowledgements: The csc220 program5 List class with a few modifications.
 */
package csc220.program5.tharpaj2020;

import csc220.program5.sample.*;
import csc220.list.AddIterator;
import csc220.list.Node;
import java.util.NoSuchElementException;

public class List<E> extends csc220.list.List<E> { 
       
   @Override
   public AddIterator<E> addIterator(){return new ListAddIterator<>();}
   
   
   //  This nested class replaces the one in csc220.list.List
   protected class ListAddIterator<T extends E> extends csc220.list.List<E>.ListAddIterator<E> {
       
       private static final boolean DEBUGGING = true;
      
        protected Node<E> prevNode = null;
        
        @Override
        public E next() {
            prevNode= nextNode;
            return super.next();
        }

        // For debugging purposes.
        private void printNodes(String s) {
            if (!DEBUGGING)
                return;
            System.out.print(s);
            if (prevNode != null) {
                System.out.print("--previous data = " + prevNode.data);
            }
            if (nextNode != null) {
                System.out.print("--next data = " + nextNode.data);               
            }
            System.out.println("");
        }
        
        @Override 
        public void addBeforeNext(E e) {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            Node<E> newNode = new Node();
            newNode.data = e;
            newNode.next = prevNode.next;
            prevNode.next = newNode;
        }
        
        @Override
        public void remove() {
            Node<E> tmp = new Node<>();
            while(tmp != prevNode){
                tmp = prevNode;
            }
            if(prevNode == nextNode){
                tmp.next = nextNode;
            }
            if(first != prevNode){
                first = nextNode;
                prevNode = null;
            }
            if(last == prevNode){
                last = nextNode;
                prevNode = null;
            }
        }
     }
   
   
}
