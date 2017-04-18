/* AddIterator.java
 * 
 * An AddIterator is an object that, besides implementing the Iterator interface,
 * also has an AddBeforeNext method.
 *
 * K. Weber
 * weberk@mountunion.edu
 * April 14, 2017.
 *
 */

package csc220.list;
public interface AddIterator<E> extends java.util.Iterator<E> {
    public void addBeforeNext(E e);
}
