package excercise;

/**
 * @author Ydiel Z. Flores Torres
 *
 */
public class SortedArrayList<E extends Comparable<? super E>> extends AbstractSortedList<E> {
	
	private E[] elements;

	
	@SuppressWarnings("unchecked")
	public SortedArrayList(int initialCapacity) {
		if (initialCapacity < 1)
			throw new IllegalArgumentException("Capacity must be at least 1");
		elements = (E[]) new Comparable[initialCapacity]; // Cannot use Object here
		currentSize = 0;
	}

	@Override
	public void add(E e) {
		if (size() == elements.length)
			reAllocate();
		int index = getIndex(e); // Index where e should be added
		/* TODO ADD CODE HERE */
		if(index == 0 && size() == 0) { // if add at the start and the list is empty
			elements[0] = e;
		}else if(index == size() && elements[index] == null) { //if adds at the end if the last position is null
			elements[index] = e;
		}else {
			moveToTheRight(index, elements, e);
		}
		currentSize++;
	}
	private void moveToTheRight(int index, E[] elements, E e) {
		
		for(int i = size() -1; i>=index; i--) {
			E mover = elements[i];
			elements[i+1] = mover;
		}
		elements[index] = e;
	}
	/**
	 * Return the index of the first occurrence of a value.
	 * If not in the list, return the index where it would be inserted.
	 * 
	 * @param e The value being searched for
	 * @return  The index where the value is or belongs
	 */
	private int getIndex(E e) {
		if (isEmpty())
			return 0;
		/* We perform binary search recursively to find the result */
		return binarySearch(e, 0, size() - 1);
	}
	
	/**
	 * This version of binary search specifically looks for the index of the 
	 * first occurrence of a value.  If the value is not in the list, then we
	 * return the index where it would be inserted, preserving the order.
	 *
	 * @param e     The value being searched for
	 * @param first First index of the array portion being searched
	 * @param last  Last  index of the array portion being searched
	 * @return      The index where the value is or belongs
	 */
	private int binarySearch(E e, int first, int last) {
		if (first == last) {
			/* We have only one element, so e belongs either before or after it */
			if (elements[first].compareTo(e) >= 0)
				return first;
			else
				return first + 1;
		}

		int mid = (first + last) / 2;
		/* To find the position, we need to find a value greater than or equal to e,
		 * either at the beginning of the list or with a lower value preceding */
		if (elements[mid].compareTo(e) >= 0 && (mid == first || elements[mid-1].compareTo(e) < 0))
			return mid;
		else if (elements[mid].compareTo(e) < 0)
			return binarySearch(e, mid+1, last);
		else
			return binarySearch(e, first, mid-1);
	}

	/**************************************************************************
	 * TODO Exercise 3
	 * 
	 * Q: Could we also use binary search in SortedLinkedList?
	 *    If you answered no, why not?
	 *    If you answered yes, why didn't/shouldn't we do it?
	 * 
	 * A: ENTER YOUR ANSWER HERE (USE AS MANY LINES AS NECESSARY)
	 * 
	 * It is possible to use binary search with LinkedList with for but we shouldn't.
	 * Every time that you need to look for the 'last' element you would have to run through 
	 * the entire LinkedList to find the last element. In my opinion, this would be extremely 
	 * inefficient when compared to an array.   
	 */
	
	@SuppressWarnings("unchecked")
	private void reAllocate() {
		E[] newElements = (E[]) new Comparable[2*size()]; // Cannot use Object here
		System.arraycopy(elements, 0, newElements, 0, size());
		elements = newElements;
	}

	@Override
	public boolean remove(E e) {
		int index = firstIndex(e);
		if (index != -1) { // Found it!
			removeIndex(index);
			return true;
		}
		return false;
	}

	@Override
	public E removeIndex(int index) {
		/* TODO ADD CODE HERE */
		E toReturn;
		if(index == size()) {
			toReturn = elements[index];
			elements[index] = null;
			currentSize--;
			return toReturn;
		}else {
			return moveToTheLeft(index, elements);
		}
		
	}
	
	private E moveToTheLeft(int index, E[] elements) {
		E toReturn = elements[index];
		elements[index] = null;
		for(int i = index; i < size()-1; i++) {
			elements[i] = elements[i+1];
			elements[i+1] = null;
		}
		currentSize--;
		return toReturn;
	}

	@Override
	public int firstIndex(E e) {
		int index = getIndex(e); // Index of where it is, or where it belongs
		if (index < size() && elements[index].equals(e)) // Found it!
			return index;
		return -1; // Didn't find it
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException();
		return elements[index];
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray() {
		E[] asArray = (E[]) new Comparable[size()]; // Cannot use Object here
		System.arraycopy(elements, 0, asArray, 0, size());
		return asArray;
	}
}