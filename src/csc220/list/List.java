/*
 * A simple linked list implementation.
 *
 * K. Weber weberk@mountunion.edu
 *
 * Original version from 2009 (or earlier).
 * March 16, 2015: Modified to be include in CSC 220 Lab 7.
 *                 Strips down to the add and iterator methods.
 * March 21, 2015: Changed ListIterator to use nextNode instance
 *                 variable instead of current.
 * March 24, 2015: Added explicit constructors for List and 
 *                 ListIterator.
 * March 22, 2017: Renamed and repackaged class.
 * April  8, 2017: Added default for AddIterator (throws UnsupportedOperationException)
 * April 14, 2017: Cleaned up generics
 * 
 */

package csc220.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class List<E> implements Iterable<E> {
    
    //  Instance variables for List.
    protected Node<E> first, last;
    
    //  One constructor.
    public List() {
        first = null;
        last = null;
    }
    
    //  Methods for List.
    public void add(E e) {
        Node<E> newNode = new Node();
        newNode.data = e;
        newNode.next = null; //  This node is always last.
        
        if (first == null) {
            first = newNode;    // Empty list
        } else {
            last.next = newNode;//  List already has at least one node.
        }
        last = newNode;
    }

    @Override
    public Iterator<E> iterator(){return new ListIterator<>();}
    
    //  This class will be extended by you to implement addBeforeNext.
    protected class ListAddIterator<T extends E> extends ListIterator<E> implements AddIterator<E> {
        @Override
        public void addBeforeNext(E e) {
            throw new UnsupportedOperationException("Not supported."); 
        }
    }
    
    public AddIterator<E> addIterator(){return new ListAddIterator<>();}
    
    //  Nested class declaration for ListIterator.
    //  Since it's nested, it DOES have access to instance variables of 
    //  List.
    private class ListIterator<T extends E> implements Iterator<E> {
        //  Instance variables for ListIterator. 
        protected Node<E> nextNode;
        
        //  One constructor
        public ListIterator(){nextNode = first;}
        
        //  hasNext() returns true precisely when nextNode is not null,
        //  and false otherwise.
        @Override
        public boolean hasNext()  {
            return (nextNode != null);
        }

        //  The method next() should make a temporary variable named tmpData,
        //  initializing tmpData with the data in nextNode,
        //  then change nextNode to point to the next node in the list, 
        //  then return the contents of tmpData.
        //  One final note: this method must first check to see whether there 
        //  are more nodes "in" the iterator before it tries to do its task.
        //  If there are no more nodes, it must throw a new NoSuchElementException.
        //  Hint: which method checks to see whether there is a next node?
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E tmpData = nextNode.data;
            nextNode = nextNode.next;
            return tmpData;
        }

        //  remove() should remain unsupported.
    }
    
}